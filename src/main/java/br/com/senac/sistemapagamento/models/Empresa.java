package br.com.senac.sistemapagamento.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import br.com.senac.sistemapagamento.models.DadosBancario;
import jakarta.persistence.FetchType;
import java.util.ArrayList;

/**
 *
 * @author alanm
 */
@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "uf")
    private String uf;
    
    @Column(name = "cidade")
    private String cidade;
    
    @Column(name = "endereco")
    private String endereco;
    
    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<DadosBancario> dadosBancarios = new ArrayList<>();

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos;

    protected Empresa() {}

    public Empresa(int id, String nome, String cnpj, String uf, String cidade, String endereco, String telefone, List<Pagamento> pagamentos) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.uf = uf;
        this.cidade = cidade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.pagamentos = pagamentos;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<DadosBancario> getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(List<DadosBancario> dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    


}
