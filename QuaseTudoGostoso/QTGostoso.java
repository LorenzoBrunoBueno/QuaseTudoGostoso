import java.util.Scanner;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


public class QTGostoso {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        /*SortedSet<Usuario> usuarios = new TreeSet<>();
        SortedSet<Categoria> categorias = new TreeSet<>();
        SortedSet<Comentario> comentarios = new TreeSet<>();
        SortedSet<Receita> receitas = new TreeSet<>();
        SortedSet<CategoriaReceita> categoriareceita = new TreeSet<>();*/
        /*Usuario usuarios = new Usuario();
        /*Categoria categorias = new Categoria();
        Comentario comentarios = new Comentario();
        Receita receitas = new Receita();
        CategoriaReceita categoriareceitas = new CategoriaReceita();*/
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);

        server.createContext("/receitas", new Receita());
        server.createContext("/comentarios", new Comentario());
        server.createContext("/categorias", new Categoria());
        server.createContext("/usuarios", new Usuario());
        server.createContext("/categoriasreceitas", new CategoriaReceita());


        server.setExecutor(null);
        server.start();
        System.out.println("Servidor rodando em http://localhost:8888/");


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
                    for (Usuario usuario : Usuario.getUsuarios()) {
                    System.out.println(" \n Informações do Usuário");
                    System.out.println(usuario);
                    Usuario usu = Usuario.usuarios.stream()
                        .filter(u -> u.getID() == usuario.idusuario)
                        .findFirst()
                        .orElse(null);
                    for (Comentario comentario : usu.comentarios) {
                        System.out.println("\n Comentario ID: " + comentario.idcomentario + ", Comentario: " + comentario.comentarioDesc);
                    }
                    for (Receita receita : usu.receitas) {
                        System.out.println("\n Receita ID: " + receita.getID() + ", Titulo: " + receita.getTitulo());
                    }
                }

                
                break;
             case "2":
                    System.out.print("Digite o id do Usuário: ");
                    int idusuario = Integer.parseInt(scanner.nextLine());
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

                    
                    Usuario b = new Usuario(idusuario, nome, email, data_nascimento, cep, genero, senha, salt, inscrito, uuid);
                    Usuario.addUsuario(b);
                    //usuarios.add(b);
                break;
             case "3":
                for (Categoria categoria: Categoria.getCategorias()) {
                    System.out.println("Informações da categoria");
                    System.out.println(categoria);
                    Categoria cate = Categoria.categorias.stream()
                        .filter(u -> u.getID() == categoria.getID())
                        .findFirst()
                        .orElse(null);
                    for (CategoriaReceita categoriaReceita : cate.categoriasreceitas){
                        System.out.println("\n ID CategoriaReceita: " + categoriaReceita.idcategoriareceita + ", Categoria: " + categoriaReceita.categoria);
                    }
                }
                break;
             case "4":
                    System.out.print("Digite o  ID da Categoria: ");
                    int idcategoria = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o nome da Categoria: ");
                    String categoriaDesc = scanner.nextLine();
                    System.out.print("Digite o estado: 1 para ativo, 2 para não ativo ");
                    int ativo = Integer.parseInt(scanner.nextLine());
                    Categoria a = new Categoria(idcategoria, categoriaDesc, ativo);
                    Categoria.addCategoria(a);
                break;
             case "5":
                for (Receita receita: Receita.getReceitas()) {
                    System.out.println("Informações da Receita");
                    System.out.println(receita);
                    Receita rece = Receita.receitas.stream()
                        .filter(u -> u.getID() == receita.getID())
                        .findFirst()
                        .orElse(null);
                    for (Comentario comentario : rece.comentarios) {
                        System.out.println("\n Comentario ID: " + comentario.idcomentario + ", Comentario: " + comentario.comentarioDesc);
                    }
                    for (CategoriaReceita categoriaReceita : rece.categoriasreceitas){
                        System.out.println("\n ID CategoriaReceita: " + categoriaReceita.idcategoriareceita + ", Categoria: " + categoriaReceita.categoria);
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
                    System.out.println("Insira o id do usuario autor das opções abaixo: ");
                    for (Usuario usuario : Usuario.usuarios){
                        System.out.println("Usuario ID: "+usuario.idusuario+", Usuario nome: "+usuario.nome);
                    }
                    int autor = Integer.parseInt(scanner.nextLine());
                    Usuario usu = Usuario.usuarios.stream()
                        .filter(u -> u.getID() == autor)
                        .findFirst()
                        .orElse(null);
                    Receita d = new Receita(idreceita, titulo, descricao, imagem, usu);
                    Receita.addReceita(d);
                break;
             case "7":
                for (Comentario comentario: Comentario.getComentarios()) {
                    System.out.println("Informações do Comentário");
                    System.out.println(comentario);
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
                    System.out.println("Insira o id do usuario autor das opções abaixo: ");
                    for (Usuario usuario : Usuario.usuarios){
                        System.out.println("Usuario ID: "+usuario.idusuario+", Usuario nome: "+usuario.nome);
                    }
                    autor = Integer.parseInt(scanner.nextLine());
                    usu = Usuario.usuarios.stream()
                        .filter(u -> u.getID() == autor)
                        .findFirst()
                        .orElse(null);
                    System.out.println("Insira o id da receita alvo do comentario das opções abaixo: ");
                    for (Receita receita : Receita.receitas){
                        System.out.println("ID: "+receita.getID()+", Titulo da Receita: "+receita.getTitulo());
                    }
                    int autorComen = Integer.parseInt(scanner.nextLine());
                    Receita rece = Receita.receitas.stream()
                        .filter(u -> u.getID() == autorComen)
                        .findFirst()
                        .orElse(null);
                    Comentario c = new Comentario(idcomentario, comentarioDesc, nota, datacomentario, usu, rece);
                    Comentario.addComentario(c);
                break;
             case "9":
                for (CategoriaReceita categoriareceitas: CategoriaReceita.getCategoriaReceitas()) {
                    System.out.println("Informações do CategoriaReceitas");
                    System.out.println(categoriareceitas);
                }
                break;
             case "10":
                    System.out.println("Insira o id da categoria receita: ");
                    int idcategoriareceita = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o id da receita das opções abaixo: ");
                    for (Receita receita : Receita.receitas){
                        System.out.println("ID: "+receita.getID()+", Titulo da Receita: "+receita.getTitulo());
                    }
                    int receita_idreceita = Integer.parseInt(scanner.nextLine());
                    rece = Receita.receitas.stream()
                        .filter(u -> u.getID() == receita_idreceita)
                        .findFirst()
                        .orElse(null);
                    System.out.println("Insira o id da categoria das opções abaixo: ");
                    for (Receita receita : Receita.receitas){
                        System.out.println("ID: "+receita.getID()+", Titulo da Receita: "+receita.getTitulo());
                    }
                    int categoria_idcategoria = Integer.parseInt(scanner.nextLine());
                    Categoria cate = Categoria.categorias.stream()
                        .filter(u -> u.getID() == categoria_idcategoria)
                        .findFirst()
                        .orElse(null);
                    CategoriaReceita e = new CategoriaReceita(idcategoriareceita, rece, cate);
                    CategoriaReceita.addCategoriaReceita(e);
                break;
            }
        }while(!op.equals( "11"));
        scanner.close();
    }
}
