package models;

/**
 *
 * @author mathe
 */
public class Linguagem {
    private int id;
    private String nome;
    private String anoLancamento;
    private String licenca;
    private String descricao;
    private String caminhoLogo;

    public Linguagem() {
        
    }

    public Linguagem(String nome, String anoLancamento, String licenca, String descricao, String caminhoLogo) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.licenca = licenca;
        this.descricao = descricao;
        this.caminhoLogo = caminhoLogo;
    }

    
    
    public String getCaminhoLogo() {
        return caminhoLogo;
    }

    public void setCaminhoLogo(String caminhoLogo) {
        this.caminhoLogo = caminhoLogo;
    }
        
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the anoLancamento
     */
    public String getAnoLancamento() {
        return anoLancamento;
    }

    /**
     * @param anoLancamento the anoLancamento to set
     */
    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    /**
     * @return the licenca
     */
    public String getLicenca() {
        return licenca;
    }

    /**
     * @param licenca the licenca to set
     */
    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.nome;    //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
