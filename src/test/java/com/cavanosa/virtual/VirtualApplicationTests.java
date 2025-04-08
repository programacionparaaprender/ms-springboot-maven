package com.cavanosa.virtual;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cavanosa.virtual.repository.TioRepository;
import com.cavanosa.virtual.service.TioService;


@SpringBootTest
class VirtualApplicationTests {

	@MockBean
    private TioRepository tioRepository;
  
    @Autowired
    private TioService tioService;

    @BeforeEach
	void setUp() {
//		cuentaRepository = mock(CuentaRepository.class);
//		bancoRepository = mock(BancoRepository.class);
//		service = new CuentaServiceImpl(cuentaRepository, bancoRepository);
//		Datos.CUENTA_001.setSaldo(new BigDecimal("1000"));
//		Datos.CUENTA_002.setSaldo(new BigDecimal("2000"));
//		Datos.BANCO.setTotalTransferencias(0);
	}
      /*  
	@Test
    void contextLoads() {
 
        Tio tio = new Tio(2,"amiya", "amiya@gmail.com", "123456");
        Tio esperado = new Tio(2,"amiya", "amiya@gmail.com", "123456");
        when(tioRepository.save(tio)).thenReturn(esperado);
        Tio resultado = tioRepository.save(tio);
        //comparando objetos
        assertEquals(esperado, resultado);
    
    }
*/
	
}
