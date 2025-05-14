package br.com.senac.sistemapagamento.repositories;

import br.com.senac.sistemapagamento.models.DadosBancario;
import br.com.senac.sistemapagamento.models.Empresa;
import java.util.List;

public interface EmpresaRepository {
    Empresa buscarPorCnpj(String cnpj);
    List<Empresa> listarEmpresasComDadosBancarios();
    void excluirEmpresa(int id);
    void cadastrarEmpresa(Empresa empresa, List<DadosBancario> dadosBancarios);
    List<Empresa> listarEmpresasPorNome(String filtroNome);
}

