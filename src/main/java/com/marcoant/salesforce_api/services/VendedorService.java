package com.marcoant.salesforce_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.dto.ListVendedorDTO;
import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.exceptions.ApiException;
import com.marcoant.salesforce_api.repository.VendedorRepository;
import com.marcoant.salesforce_api.services.interfaces.IVendedorService;
import com.marcoant.salesforce_api.utils.ValidatorUtil;

import jakarta.transaction.Transactional;

@Service
public class VendedorService implements IVendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private FilialService filialService;

    public List<CreateVendedorDTO> getAllVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return CreateVendedorDTO.fromVendedorList(vendedores);
    }

    @Transactional
    public CreateVendedorDTO createVendedor(CreateVendedorDTO vendedorDTO) {
        ValidatorUtil.validateDocument(vendedorDTO);
        String matricula = ValidatorUtil.generateMatricula(vendedorDTO.getTipoContratacao());

        Optional<Vendedor> existingVendedorByEmail = vendedorRepository.findByEmail(vendedorDTO.getEmail());
        if (existingVendedorByEmail.isPresent()) {
            throw new ApiException("E-mail já está em uso", 400);
        }

        Optional<Vendedor> existingVendedorByCpfCnpj = vendedorRepository.findByCpfCnpj(vendedorDTO.getCpfCnpj());
        if (existingVendedorByCpfCnpj.isPresent()) {
            throw new ApiException("CPF ou CNPJ já está em uso", 400);
        }

        FilialDTO filialDTO = filialService.getFilialById(vendedorDTO.getFilialId());
        if (filialDTO == null) {
            throw new ApiException("Filial não encontrada", 404);
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
        return CreateVendedorDTO.fromVendedor(vendedor);
    }

    @Transactional
    public CreateVendedorDTO updateVendedor(Long id, CreateVendedorDTO vendedorDTO) {
        ValidatorUtil.validateDocument(vendedorDTO);
        Optional<Vendedor> vendedorOp = vendedorRepository.findById(id);
        if (!vendedorOp.isPresent()) {
            throw new ApiException("Vendedor não encontrado", 404);
        }

        Vendedor vendedor = vendedorOp.get();

        Optional<Vendedor> existingVendedorByEmail = vendedorRepository.findByEmail(vendedorDTO.getEmail());
        if (existingVendedorByEmail.isPresent() && !existingVendedorByEmail.get().getId().equals(id)) {
            throw new ApiException("E-mail já está em uso", 400);
        }

        Optional<Vendedor> existingVendedorByCpfCnpj = vendedorRepository.findByCpfCnpj(vendedorDTO.getCpfCnpj());
        if (existingVendedorByCpfCnpj.isPresent() && !existingVendedorByCpfCnpj.get().getId().equals(id)) {
            throw new ApiException("CPF ou CNPJ já está em uso", 400);
        }

        vendedor.setNome(vendedorDTO.getNome());
        vendedor.setCpfCnpj(vendedorDTO.getCpfCnpj());
        vendedor.setEmail(vendedorDTO.getEmail());
        vendedor.setDataNascimento(vendedorDTO.getDataNascimento());
        vendedor.setTipoContratacao(vendedorDTO.getTipoContratacao());
        vendedorRepository.save(vendedor);
        return CreateVendedorDTO.fromVendedor(vendedor);
    }

    public void deleteVendedor(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        if (vendedor.isPresent()) {
            vendedorRepository.delete(vendedor.get());
        } else {
            throw new ApiException("Vendedor não encontrado", 404);
        }
    }

    public ListVendedorDTO getVendedorWithFilial(String matricula) {
        Optional<Vendedor> vendedorOp = vendedorRepository.findByMatricula(matricula);
        if (!vendedorOp.isPresent()) {
            throw new ApiException("Vendedor não encontrado", 404);
        }

        Vendedor vendedor = vendedorOp.get();
        FilialDTO filialDTO = filialService.getFilialById(vendedor.getFilialId());
        if (filialDTO == null) {
            throw new ApiException("Filial não encontrada", 404);
        }

        ListVendedorDTO listVendedorDTO = new ListVendedorDTO();
        listVendedorDTO.setMatricula(vendedor.getMatricula());
        listVendedorDTO.setNome(vendedor.getNome());
        listVendedorDTO.setDataNascimento(vendedor.getDataNascimento());
        listVendedorDTO.setCpfCnpj(vendedor.getCpfCnpj());
        listVendedorDTO.setEmail(vendedor.getEmail());
        listVendedorDTO.setTipoContratacao(vendedor.getTipoContratacao());
        listVendedorDTO.setFilialId(vendedor.getFilialId());
        listVendedorDTO.setFilial(filialDTO);

        return listVendedorDTO;
    }
}
