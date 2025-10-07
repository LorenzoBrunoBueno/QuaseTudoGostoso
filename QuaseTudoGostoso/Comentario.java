public class Comentario implements Comparable<Comentario>{
    int idcomentario;
    String comentarioDesc;
    int nota;
    String datacomentario;
    Usuario usuario;
    Receita receita;

    public Comentario(int idcomentario, String comentarioDesc, int nota, String datacomentario, Usuario usuario, Receita receita){
        this.idcomentario = idcomentario;
        this.comentarioDesc = comentarioDesc;
        this.nota = nota;
        this.datacomentario = datacomentario;
        this.usuario = usuario;
        this.receita = receita;
        receita.comentarios.add(this);
        usuario.comentarios.add(this);
    }

    @Override
    public int compareTo(Comentario other) {
        // Example: Sort by 'nome' in ascending order
        return this.datacomentario.compareTo(other.datacomentario);
    }

}
