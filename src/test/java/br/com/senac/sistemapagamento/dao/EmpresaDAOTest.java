/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DadosBancario;
import br.com.senac.sistemapagamento.models.Empresa;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alanm
 */
public class EmpresaDAOTest {

    public EmpresaDAOTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testAssociarEmpresaComDadosBancariosSemPersistencia() {
        // Criar empresa com o construtor completo
        Empresa empresa = new Empresa(
                1,
                "Alfa Ltda",
                "12.345.678/0001-90",
                "SP",
                "São Paulo",
                "Rua das Flores, 123",
                "(11) 99999-8888",
                new ArrayList<>()
        );

        // Criar dado bancário associado à empresa
        DadosBancario dado = new DadosBancario(
                null,
                104,
                "Caixa Econômica",
                "Corrente",
                1234,
                0,
                98765,
                1,
                "alfa@pix.com",
                "Alfa Ltda",
                "12.345.678/0001-90",
                empresa
        );

        // Associar dado à empresa
        List<DadosBancario> dados = new ArrayList<>();
        dados.add(dado);
        empresa.setDadosBancarios(dados);

        // Validações
        assertEquals("Alfa Ltda", empresa.getNome());
        assertEquals(1, empresa.getDadosBancarios().size());
        assertEquals(empresa, dado.getEmpresa());
        assertEquals("Caixa Econômica", dado.getNomeBanco());
    }
}
