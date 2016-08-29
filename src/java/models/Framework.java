package models;

/**
 *
 * @author mathe
 */
public class Framework {
    private int id;
    private String nome;
    private String descricao;
    private String genero;
    private String paginaOficial;
    private int idLinguagem;
    private String caminhoLogo;

    public Framework() {
        
    }

    public Framework(String nome, String descricao, String genero, String paginaOficial, int idLinguagem, String caminhoLogo) {
        this.nome = nome;
        this.descricao = descricao;
        this.genero = genero;
        this.paginaOficial = paginaOficial;
        this.idLinguagem = idLinguagem;
        this.caminhoLogo = caminhoLogo;
    }

    public String getCaminhoLogo() {
        return caminhoLogo;
    }

    public void setCaminhoLogo(String caminhoLogo) {
        this.caminhoLogo = caminhoLogo;
    }
    
    

    public int getIdLinguagem() {
        return idLinguagem;
    }

    public void setIdLinguagem(int idLinguagem) {
        this.idLinguagem = idLinguagem;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPaginaOficial() {
        return paginaOficial;
    }

    public void setPaginaOficial(String paginaOficial) {
        this.paginaOficial = paginaOficial;
    }
    
    
}
