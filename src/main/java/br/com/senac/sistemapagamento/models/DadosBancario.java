package br.com.senac.sistemapagamento.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import br.com.senac.sistemapagamento.models.Empresa;
import jakarta.persistence.Column;

/**
 *
 * @author alanm
 */
@Entity
@Table(name = "dados_bancarios")
public class DadosBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "codigo_banco")
    private Integer codigoBanco;
    
    @Column(name = "nome_banco")
    private String nomeBanco;
    
    @Column(name = "tipo_conta")
    private String tipoConta;
    
    @Column(name = "agencia")
    private Integer agencia;
    
    @Column(name = "digito_agencia")
    private Integer digitoAgencia;
    
    @Column(name = "conta")
    private Integer conta;
    
    @Column(name = "digito_conta")
    private Integer digitoConta;
    
    @Column(name = "chave_pix")
    private String chavePix;
    
    @Column(name = "nome_recebedor")
    private String nomeRecebedor;
    
    @Column(name = "cnpj_recebedor")
    private String cnpjRecebedor;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    protected DadosBancario() {}

    public DadosBancario(Integer id, Integer codigoBanco, String nomeBanco, String tipoConta, Integer agencia, Integer digitoAgencia, Integer conta, Integer digitoConta, String chavePix, String nomeRecebedor, String cnpjRecebedor, Empresa empresa) {
        this.id = id;
        this.codigoBanco = codigoBanco;
        this.nomeBanco = nomeBanco;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.digitoAgencia = digitoAgencia;
        this.conta = conta;
        this.digitoConta = digitoConta;
        this.chavePix = chavePix;
        this.nomeRecebedor = nomeRecebedor;
        this.cnpjRecebedor = cnpjRecebedor;
        this.empresa = empresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(Integer codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(Integer digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Integer getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(Integer digitoConta) {
        this.digitoConta = digitoConta;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getNomeRecebedor() {
        return nomeRecebedor;
    }

    public void setNomeRecebedor(String nomeRecebedor) {
        this.nomeRecebedor = nomeRecebedor;
    }

    public String getCnpjRecebedor() {
        return cnpjRecebedor;
    }

    public void setCnpjRecebedor(String cnpjRecebedor) {
        this.cnpjRecebedor = cnpjRecebedor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
}
