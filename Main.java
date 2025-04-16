class Livro{
    String titulo;
    String autor;
    int ano;

    public Livro(String titulo, String autor, int ano){
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;

    }

    public String toString() {
        return titulo + " por " + autor + " (" + ano + ")";

    }
}
class Node {
    Livro livro;
    Node esquerda;
    Node direita;

    public Node(Livro livro){
        this.livro = livro;
    }
}
class arvoreDeLivros {
    Node raiz;
    public arvoreDeLivros(){
        raiz = null;
    }

    // inserir
    public void inserir(Livro livro){
        raiz = inserirNodeRecursivamente(raiz, livro);
    }

    private Node inserirNodeRecursivamente(Node atual, Livro livro){
        if(atual == null) return new Node(livro);

        if (livro.titulo.compareTo(atual.livro.titulo) < 0) {
            atual.esquerda = inserirNodeRecursivamente(atual.esquerda, livro);
        } else {
            atual.direita = inserirNodeRecursivamente(atual.direita, livro);
        }
        return atual;
    }

    // Remover

    // Procurar valor
    public boolean procurarLivro(String titulo){
        return procurarLivroRecursivamente(raiz, titulo);
    }
    private boolean procurarLivroRecursivamente(Node atual, String titulo){
        if (atual == null) return false;

        if (atual.livro.titulo.equals(titulo)){
            return true;
        }
        return titulo.compareTo(atual.livro.titulo) < 0
                ? procurarLivroRecursivamente(atual.esquerda, titulo)
                : procurarLivroRecursivamente(atual.direita, titulo);
    }

    // Procurar maior

    // Mostrar arvore
    public void mostrarArvore(){
        mostrarRecursivamente(raiz, 0);
    }

    private void mostrarRecursivamente(Node atual, int nivel){
        if(atual == null) return;
        if (nivel > 0){
            System.out.print("     ".repeat(nivel - 1 )+ "└----");
        }
        System.out.println(atual.livro);
        mostrarRecursivamente(atual.esquerda, nivel + 1);
        mostrarRecursivamente(atual.direita, nivel + 1);
    }
}
public class Main{
    public static void main(String[] args) {
        arvoreDeLivros biblioteca = new arvoreDeLivros();
        biblioteca.mostrarArvore();
        biblioteca.inserir(new Livro("O Cortiço", "Aluísio Azevedo", 1890));
        biblioteca.inserir(new Livro("Quincas Borba", "Machado de Assis", 1891));
        biblioteca.inserir(new Livro("Estrela da Vida Inteira", "Manuel Bandeira", 1966));
        biblioteca.inserir(new Livro("Recordações do Escrivão Isaías Caminha", "Lima Barreto", 1909));
        biblioteca.inserir(new Livro("Sermões", "Padre Antônio Vieira", 1600)); // coleção de sermões
        biblioteca.inserir(new Livro("Niketche: Uma História de Poligamia", "Paulina Chiziane", 2002));
        biblioteca.inserir(new Livro("Campo Geral", "João Guimarães Rosa", 1964));
        biblioteca.inserir(new Livro("O Uraguai", "Basílio da Gama", 1769));
        biblioteca.inserir(new Livro("Novelas Exemplares", "Miguel de Cervantes", 1613));
        biblioteca.inserir(new Livro("O Rei da Vela", "Oswald de Andrade", 1933));

        biblioteca.mostrarArvore();

        System.out.println("Buscando livro `O Uraguai`: " + biblioteca.procurarLivro("O Uraguai"));
        System.out.println("Buscando livro `1984`: " + biblioteca.procurarLivro("1984"));

    }
}
