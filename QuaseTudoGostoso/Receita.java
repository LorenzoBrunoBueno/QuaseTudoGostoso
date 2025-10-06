import java.util.ArrayList;

public class Receita implements Comparable<Receita>{
    int idreceita;
    String titulo;
    String descricao;
    String imagem;
    Usuario usuario;

     ArrayList<Comentario> comentarios = new ArrayList<>();

    public Receita(int idreceita, String titulo, String descricao, String imagem, Usuario usuario){
        this.idreceita = idreceita;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        usuario.receitas.add(this);
    }

    @Override
    public int compareTo(Receita other) {
        // Example: Sort by 'nome' in ascending order
        return this.titulo.compareTo(other.titulo);
    }
}