package com.marcoant.salesforce_api.unit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.exceptions.ApiException;
import com.marcoant.salesforce_api.repository.VendedorRepository;
import com.marcoant.salesforce_api.services.FilialService;
import com.marcoant.salesforce_api.services.VendedorService;
import com.marcoant.salesforce_api.unit.services.mocks.VendedorMock;

@ExtendWith(MockitoExtension.class)
public class VendedorServiceTeste {

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private FilialService filialService;

    @InjectMocks
    private VendedorService vendedorService;

    private CreateVendedorDTO createVendedorDTO;
    private Vendedor vendedor;
    private FilialDTO filialDTO;

    @BeforeEach
    public void setUp() {
        createVendedorDTO = VendedorMock.createVendedorDTO();
        vendedor = VendedorMock.vendedor();
        filialDTO = VendedorMock.filialDTO();
    }

    @Test
    public void testCreateVendedor_success() {
        when(vendedorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(vendedorRepository.findByCpfCnpj(anyString())).thenReturn(Optional.empty());
        when(filialService.getFilialById(anyLong())).thenReturn(filialDTO);
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor);

        CreateVendedorDTO result = vendedorService.createVendedor(createVendedorDTO);

        assertNotNull(result);
        assertEquals(createVendedorDTO.getNome(), result.getNome());
        verify(vendedorRepository, times(1)).findByEmail(anyString());
        verify(vendedorRepository, times(1)).findByCpfCnpj(anyString());
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
    }

    @Test
    public void testCreateVendedor_filialNotFound() {
        when(vendedorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(vendedorRepository.findByCpfCnpj(anyString())).thenReturn(Optional.empty());
        when(filialService.getFilialById(anyLong())).thenReturn(null);

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.createVendedor(createVendedorDTO);
        });

        assertEquals("Filial não encontrada", exception.getMessage());
        verify(vendedorRepository, times(1)).findByEmail(anyString());
        verify(vendedorRepository, times(1)).findByCpfCnpj(anyString());
        verify(vendedorRepository, never()).save(any(Vendedor.class));
    }

    @Test
    public void testCreateVendedor_emailAlreadyInUse() {
        when(vendedorRepository.findByEmail(anyString())).thenReturn(Optional.of(vendedor));

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.createVendedor(createVendedorDTO);
        });

        assertEquals("E-mail já está em uso", exception.getMessage());
        verify(vendedorRepository, times(1)).findByEmail(anyString());
        verify(vendedorRepository, never()).save(any(Vendedor.class));
    }

    @Test
    public void testCreateVendedor_cpfCnpjAlreadyInUse() {
        when(vendedorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(vendedorRepository.findByCpfCnpj(anyString())).thenReturn(Optional.of(vendedor));

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.createVendedor(createVendedorDTO);
        });

        assertEquals("CPF ou CNPJ já está em uso", exception.getMessage());
        verify(vendedorRepository, times(1)).findByEmail(anyString());
        verify(vendedorRepository, times(1)).findByCpfCnpj(anyString());
        verify(vendedorRepository, never()).save(any(Vendedor.class));
    }
}
