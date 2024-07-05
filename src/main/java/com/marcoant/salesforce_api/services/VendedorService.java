package com.marcoant.salesforce_api.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.dto.VendedorDTO;
import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.exceptions.ApiException;
import com.marcoant.salesforce_api.repository.VendedorRepository;
import com.marcoant.salesforce_api.services.interfaces.IVendedorService;
import com.marcoant.salesforce_api.utils.ValidatorUtil;

import jakarta.transaction.Transactional;

@Service
public class VendedorService implements IVendedorService {
    private static final Logger logger = LoggerFactory.getLogger(VendedorService.class);

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private FilialService filialService;

    public List<VendedorDTO> getAllVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return VendedorDTO.fromVendedorList(vendedores);
    }

    @Transactional
    public VendedorDTO createVendedor(VendedorDTO vendedorDTO) {
        try {
            ValidatorUtil.validateDocument(vendedorDTO);
            FilialDTO filialDTO = filialService.getFilialById(vendedorDTO.getFilialId());
            String matricula = ValidatorUtil.generateMatricula(vendedorDTO.getTipoContratacao());

            Optional<Vendedor> existingVendedorByEmail = vendedorRepository.findByEmail(vendedorDTO.getEmail());
            if (existingVendedorByEmail.isPresent()) {
                throw new ApiException("E-mail já está em uso", 400);
            }

            Optional<Vendedor> existingVendedorByCpfCnpj = vendedorRepository.findByCpfCnpj(vendedorDTO.getCpfCnpj());
            if (existingVendedorByCpfCnpj.isPresent()) {
                throw new ApiException("CPF ou CNPJ já está em uso", 400);
            }

            Vendedor vendedor = new Vendedor();
            vendedor.setNome(vendedorDTO.getNome());
            vendedor.setCpfCnpj(vendedorDTO.getCpfCnpj());
            vendedor.setEmail(vendedorDTO.getEmail());
            vendedor.setDataNascimento(vendedorDTO.getDataNascimento());
            vendedor.setMatricula(matricula);
            vendedor.setFilialId(filialDTO.getId());
            vendedor.setTipoContratacao(vendedorDTO.getTipoContratacao());
            vendedorRepository.save(vendedor);
            return VendedorDTO.fromVendedor(vendedor);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Erro ao criar vendedor: {}", e.getMessage());
            throw new ApiException("Erro ao criar vendedor", e.getMessage(), 500);
        }
    }

    @Transactional
    public VendedorDTO updateVendedor(Long id, VendedorDTO vendedorDTO) {
        try {
            ValidatorUtil.validateDocument(vendedorDTO);
            Optional<Vendedor> vendedorOp = vendedorRepository.findById(id);
            if (!vendedorOp.isPresent()) {
                throw new ApiException("Vendedor não encontrado", 404);
            }

            Vendedor vendedor = vendedorOp.get();

            Optional<Vendedor> existingVendedorByEmail = vendedorRepository.findByEmail(vendedorDTO.getEmail());
            if (existingVendedorByEmail.isPresent() && !existingVendedorByEmail.get().getId().equals(id)) {
                throw new ApiException("E-mail já está em uso por outro usuário", 400);
            }

            Optional<Vendedor> existingVendedorByCpfCnpj = vendedorRepository.findByCpfCnpj(vendedorDTO.getCpfCnpj());
            if (existingVendedorByCpfCnpj.isPresent() && !existingVendedorByCpfCnpj.get().getId().equals(id)) {
                throw new ApiException("CPF ou CNPJ já está em uso por outro usuário", 400);
            }

            vendedor.setNome(vendedorDTO.getNome());
            vendedor.setCpfCnpj(vendedorDTO.getCpfCnpj());
            vendedor.setEmail(vendedorDTO.getEmail());
            vendedor.setDataNascimento(vendedorDTO.getDataNascimento());
            vendedor.setTipoContratacao(vendedorDTO.getTipoContratacao());
            vendedorRepository.save(vendedor);
            return VendedorDTO.fromVendedor(vendedor);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Erro ao atualizar vendedor: {}", e.getMessage());
            throw new ApiException("Erro ao atualizar vendedor", e.getMessage(), 500);
        }
    }

    public void deleteVendedor(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        if (vendedor.isPresent()) {
            vendedorRepository.delete(vendedor.get());
        } else {
            throw new ApiException("Vendedor não encontrado", 404);
        }
    }

    public VendedorDTO getVendedorById(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        if (vendedor.isPresent()) {
            return VendedorDTO.fromVendedor(vendedor.get());
        }
        throw new ApiException("Vendedor não encontrado", 404);
    }

}
