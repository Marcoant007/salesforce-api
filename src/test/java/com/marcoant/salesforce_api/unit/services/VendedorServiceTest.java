package com.marcoant.salesforce_api.unit.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.dto.ListVendedorDTO;
import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.exceptions.ApiException;
import com.marcoant.salesforce_api.repository.VendedorRepository;
import com.marcoant.salesforce_api.services.FilialService;
import com.marcoant.salesforce_api.services.VendedorService;
import com.marcoant.salesforce_api.unit.services.mocks.VendedorMock;

@ExtendWith(MockitoExtension.class)
public class VendedorServiceTest {

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
    public void testCreateVendedorSuccess() {
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
    public void testCreateVendedorFilialNotFound() {
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
    public void testCreateVendedorEmailAlreadyInUse() {
        when(vendedorRepository.findByEmail(anyString())).thenReturn(Optional.of(vendedor));

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.createVendedor(createVendedorDTO);
        });

        assertEquals("E-mail já está em uso", exception.getMessage());
        verify(vendedorRepository, times(1)).findByEmail(anyString());
        verify(vendedorRepository, never()).save(any(Vendedor.class));
    }

    @Test
    public void testCreateVendedorCpfCnpjAlreadyInUse() {
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

    @Test
    public void testUpdateVendedorSuccess() {
        when(vendedorRepository.findById(anyLong())).thenReturn(Optional.of(vendedor));
        when(vendedorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(vendedorRepository.findByCpfCnpj(anyString())).thenReturn(Optional.empty());
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor);

        CreateVendedorDTO result = vendedorService.updateVendedor(1L, createVendedorDTO);

        assertNotNull(result);
        assertEquals(createVendedorDTO.getNome(), result.getNome());
    }

    @Test
    public void testUpdateVendedorNotFound() {
        when(vendedorRepository.findById(anyLong())).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.updateVendedor(1L, createVendedorDTO);
        });

        assertEquals("Vendedor não encontrado", exception.getMessage());
    }

    @Test
    public void testUpdateVendedorEmailAlreadyInUse() {
        when(vendedorRepository.findById(anyLong())).thenReturn(Optional.of(vendedor));
        Vendedor anotherVendedor = new Vendedor();
        anotherVendedor.setId(2L);
        when(vendedorRepository.findByEmail(anyString())).thenReturn(Optional.of(anotherVendedor));

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.updateVendedor(1L, createVendedorDTO);
        });

        assertEquals("E-mail já está em uso", exception.getMessage());
    }

    @Test
    public void testUpdateVendedorCpfCnpjAlreadyInUse() {
        when(vendedorRepository.findById(anyLong())).thenReturn(Optional.of(vendedor));
        Vendedor anotherVendedor = new Vendedor();
        anotherVendedor.setId(2L);
        when(vendedorRepository.findByCpfCnpj(anyString())).thenReturn(Optional.of(anotherVendedor));

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.updateVendedor(1L, createVendedorDTO);
        });

        assertEquals("CPF ou CNPJ já está em uso", exception.getMessage());
    }

    @Test
    public void testDeleteVendedorSuccess() {
        when(vendedorRepository.findById(anyLong())).thenReturn(Optional.of(vendedor));

        assertDoesNotThrow(() -> vendedorService.deleteVendedor(1L));
    }

    @Test
    public void testDeleteVendedorNotFound() {
        when(vendedorRepository.findById(anyLong())).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.deleteVendedor(1L);
        });

        assertEquals("Vendedor não encontrado", exception.getMessage());
    }

    @Test
    public void testGetAllVendedores() {
        when(vendedorRepository.findAll()).thenReturn(Arrays.asList(vendedor));

        List<CreateVendedorDTO> result = vendedorService.getAllVendedores();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(vendedor.getNome(), result.get(0).getNome());
    }

    @Test
    public void testGetVendedorWithFilialSuccess() {
        when(vendedorRepository.findByMatricula(anyString())).thenReturn(Optional.of(vendedor));
        when(filialService.getFilialById(anyLong())).thenReturn(filialDTO);

        ListVendedorDTO result = vendedorService.getVendedorWithFilial("12345678-CLT");

        assertNotNull(result);
        assertEquals(vendedor.getNome(), result.getNome());
        assertEquals(filialDTO.getNome(), result.getFilial().getNome());
    }

    @Test
    public void testGetVendedorWithFilialVendedorNotFound() {
        when(vendedorRepository.findByMatricula(anyString())).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.getVendedorWithFilial("12345678-CLT");
        });

        assertEquals("Vendedor não encontrado", exception.getMessage());
    }

    @Test
    public void testGetVendedorWithFilialFilialNotFound() {
        when(vendedorRepository.findByMatricula(anyString())).thenReturn(Optional.of(vendedor));
        when(filialService.getFilialById(anyLong())).thenReturn(null);

        ApiException exception = assertThrows(ApiException.class, () -> {
            vendedorService.getVendedorWithFilial("12345678-CLT");
        });

        assertEquals("Filial não encontrada", exception.getMessage());
    }
}
