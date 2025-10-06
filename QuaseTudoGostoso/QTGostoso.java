import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class QTGostoso {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        SortedSet<Usuario> usuarios = new TreeSet<>();
        SortedSet<Categoria> categorias = new TreeSet<>();
        SortedSet<Comentario> comentarios = new TreeSet<>();
        SortedSet<Receita> receitas = new TreeSet<>();
        SortedSet<CategoriaReceita> categoriareceita = new TreeSet<>();

        Usuario usu = null;
        Receita rece = null;
        Comentario come = null;
        Categoria cate = null;

        String op;
        do{
        System.out.println("\n\n ===Quase Tudo Gostoso===");
        System.out.println("[1] Listar Usuários");
        System.out.println("[2] Adicionar Usuário");
        System.out.println("[3] Listar Categoria");
        System.out.println("[4] Adicionar Categoria");
        System.out.println("[5] Listar Receitas");
        System.out.println("[6] Adicionar Receita");
        System.out.println("[7] Listar Comentários");
        System.out.println("[8] Adicionar Comentário");
        System.out.println("[9] Listar CategoriaReceita");
        System.out.println("[10] Adicionar CategoriaReceita");
        System.out.println("[11] Sair");
        do{
            System.out.println("Insira o número correspondente a Opção: ");
            op = scanner.nextLine();
        
        }while(!op.equals("1") && !op.equals("2") && !op.equals("3") && !op.equals("4") && !op.equals("5") && !op.equals("6") && !op.equals("7") && !op.equals("8") && !op.equals("9") && !op.equals("10") && !op.equals("11"));
        switch (op) {
            case "1":
           
                    for (Usuario usuario : usuarios) {
                    System.out.println(" \n Informações do Usuário");
                    System.out.println("Nome: " + usuario.nome);
                    System.out.println("Email: " + usuario.email);
                    System.out.println("Data de Nascimento: " + usuario.data_nascimento);
                    System.out.println("CEP: " + usuario.cep);
                    System.out.println("Gênero: " + (usuario.genero == 1 ? "Homem" : "Mulher"));
                    System.out.println("Senha: " + usuario.senha);
                    System.out.println("Salt: " + usuario.salt);
                    System.out.println("Inscrito: " + usuario.inscrito);
                    System.out.println("uuid: " + usuario.uuid);
                    for (Comentario comentario : usu.comentarios) {
                        System.out.println("\n Comentario ID: " + comentario.idcomentario + ", Comentario: " + comentario.comentarioDesc);
                    }
                    for (Receita receita : usu.receitas) {
                        System.out.println("\n Receita ID: " + receita.idreceita + ", Titulo: " + receita.titulo);
                    }
                }

                
                break;
             case "2":
                    System.out.print("Digite o nome do Usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite a data de Nascimento (dd/mm/aaaa): ");
                    String data_nascimento = scanner.nextLine();
                    System.out.print("Digite o CEP: ");
                    int cep = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o gênero (1 para Homen, 0 para Mulher):");
                    int genero = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Digite o Salt: ");
                    String salt = scanner.nextLine();
                    System.out.print("Digite o Inscrito: ");
                    String inscrito = scanner.nextLine();
                    System.out.print("Digite o uuid: ");
                    String uuid = scanner.nextLine();

                    Usuario b = new Usuario(nome, email, data_nascimento, cep, genero, senha, salt, inscrito, uuid);
                    usu =  b;
                    usuarios.add(b);
                break;
             case "3":
                for (Categoria categoria: categorias) {
                    System.out.println("Informações do Ingrediente");
                    System.out.println("ID: " + categoria.idcategoria);
                    System.out.println("Categoria: " + categoria.categoriaDesc);
                    System.out.println("Ativo: " + categoria.ativo);
                }
                break;
             case "4":
                    System.out.print("Digite o  ID da Categoria: ");
                    int idcategoria = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o Ingrediente: ");
                    String categoriaDesc = scanner.nextLine();
                    System.out.print("Digite o estado: 1 para ativo, 2 para não ativo ");
                    int ativo = Integer.parseInt(scanner.nextLine());
                    Categoria a = new Categoria(idcategoria, categoriaDesc, ativo);
                    cate = a;
                    categorias.add(a);
                break;
             case "5":
                for (Receita receita: receitas) {
                    System.out.println("Informações do Ingrediente");
                    System.out.println("ID: " + receita.idreceita);
                    System.out.println("Titulo: " + receita.titulo);
                    System.out.println("Descrição: " + receita.descricao);
                    System.out.println("Imagem: " + receita.imagem);
                    for (Comentario comentario : rece.comentarios) {
                        System.out.println("\n Comentario ID: " + comentario.idcomentario + ", Comentario: " + comentario.comentarioDesc);
                    }
                    
                }
                break;
             case "6":
                    System.out.print("Digite o  ID da Receita: ");
                    int idreceita = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite a Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite o nome da Imagem: ");
                    String imagem = scanner.nextLine();
                    Receita d = new Receita(idreceita, titulo, descricao, imagem, usu);
                    rece = d;
                    receitas.add(d);
                break;
             case "7":
                for (Comentario comentario: comentarios) {
                    System.out.println("Informações do Comentário");
                    System.out.println("Comentário: " + comentario.comentarioDesc);
                    System.out.println("Nota: " + comentario.nota);
                    System.out.println("Data do Comentário: " + comentario.datacomentario);
                    
                }
                break;
             case "8":
                    System.out.println("Digite o id do Comentário");
                    int idcomentario = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o Comentário: ");
                    String comentarioDesc = scanner.nextLine();
                    System.out.print("Digite a nota do Comentário: ");
                    int nota = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite a data do Comentário: ");
                    String datacomentario = scanner.nextLine();
                    Comentario c = new Comentario(idcomentario, comentarioDesc, nota, datacomentario, usu, rece );
                    come = c;
                    comentarios.add(c);
                break;
             case "9":
                for (CategoriaReceita categoriareceitas: categoriareceita) {
                    System.out.println("Informações do CategoriaReceitas");
                    System.out.println("Receita_idreceita: " + categoriareceitas.receita_idreceita);
                    System.out.println("Categoria_idcategoria: " + categoriareceitas.categoria_idcategoria);
                    
                }
                break;
             case "10":
                    System.out.print("Digite o idreceita: ");
                    int receita_idreceita = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o icategoria: ");
                    int categoria_idcategoria = Integer.parseInt(scanner.nextLine());
                    CategoriaReceita e = new CategoriaReceita(receita_idreceita, categoria_idcategoria);
                    categoriareceita.add(e);
                break;
            }
        }while(!op.equals( "11"));
    }
}
