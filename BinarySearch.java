
class Node {
    int valorDoNode;
    Node esquerda;
    Node direita;

    public Node(int valor) {
        this.valorDoNode = valor;
        esquerda = null;
        direita = null;

    }
}

class nossaArvoreBinaria {
    Node raiz;

    public nossaArvoreBinaria() {
        raiz = null;
    }

    // Metodos para inserir coisas na arvore
    public void inserir(int valor) {
        raiz = inserirNodeRecursivamente(raiz, valor);
    }


    private Node inserirNodeRecursivamente(Node nodeAtual, int valorASerInserido) {
        Node nossoNovoNode = new Node(valorASerInserido);
        if (nodeAtual == null) {
            return new Node(valorASerInserido);
        }
        if (valorASerInserido < nodeAtual.valorDoNode) {
            nodeAtual.esquerda = inserirNodeRecursivamente(nodeAtual.esquerda, valorASerInserido);
        } else if (valorASerInserido > nodeAtual.valorDoNode) {
            nodeAtual.direita = inserirNodeRecursivamente(nodeAtual.direita, valorASerInserido);
        }
        return nodeAtual;


    }

    // Metodo para procurar recursivamente
    public boolean procurarValor(int valorParaPesquisar) {
        return procurarValorRecursivamente(raiz, valorParaPesquisar);
    }

    private boolean procurarValorRecursivamente(Node nodeAtual, int valorParaPesquisar) {
        if (nodeAtual == null) {
            return false;
        }

        if (nodeAtual.valorDoNode == valorParaPesquisar) {
            return true;
        }
        return valorParaPesquisar < nodeAtual.valorDoNode
                ? (procurarValorRecursivamente(nodeAtual.esquerda, valorParaPesquisar))
                : (procurarValorRecursivamente(nodeAtual.direita, valorParaPesquisar));
    }

    // Metodo para remover coisas na arvore
    public void remover(int valorASerRemovido) {
        raiz = removerNodeRecursivamente(raiz, valorASerRemovido);
    }

    private Node removerNodeRecursivamente(Node nodeAtual, int valorASerRemovido) {
        if (nodeAtual == null) {
            return null;
        }
        if (valorASerRemovido == nodeAtual.valorDoNode) {
            // Ver se o node tem filhos
            if (nodeAtual.esquerda == null && nodeAtual.direita == null) {
                return null;
            }

            // o node tem somente um filho. E agora?
            if (nodeAtual.esquerda == null) {
                return nodeAtual.direita;

            }
            if (nodeAtual.direita == null) {
                return nodeAtual.esquerda;
            }
            // se o node tem dois filhos
            int maiorValorEntreOsFilhos = procurarMaiorValor(nodeAtual.esquerda);
            nodeAtual.valorDoNode = maiorValorEntreOsFilhos;
            nodeAtual.esquerda = removerNodeRecursivamente(nodeAtual.esquerda, maiorValorEntreOsFilhos);
            return nodeAtual;

        } else if (valorASerRemovido < nodeAtual.valorDoNode) {
            nodeAtual.esquerda = removerNodeRecursivamente(nodeAtual.esquerda, valorASerRemovido);
            return nodeAtual;
        } else {
            nodeAtual.direita = removerNodeRecursivamente(nodeAtual.direita, valorASerRemovido);
            return nodeAtual;
        }

    }

    private int procurarMaiorValor(Node nodeAtual) {
        return (nodeAtual.direita == null)
                ? (nodeAtual.valorDoNode) :
                (procurarMaiorValor(nodeAtual.direita));

    }



    // metodos para mostrar a arvore na tela
    public void mostrarArvore() {
        mostrarNodeRecursivamente(raiz, 0);

    }

    private void mostrarNodeRecursivamente(Node nodeAtual, int nivelDaArvore) {
        if (nodeAtual == null) {
            return;
        }
        if (nivelDaArvore > 0) {
            System.out.print("     ".repeat(nivelDaArvore - 1) + "└----");
        }
        System.out.println(nodeAtual.valorDoNode);

        mostrarNodeRecursivamente(nodeAtual.esquerda, nivelDaArvore + 1);
        mostrarNodeRecursivamente(nodeAtual.direita, nivelDaArvore + 1);
    }

}


public class Main {
    public static void main(String[] args) {
        nossaArvoreBinaria arvoreExemplo = new nossaArvoreBinaria();
        arvoreExemplo.inserir(20);
        arvoreExemplo.inserir(25);
        arvoreExemplo.inserir(10);
        arvoreExemplo.inserir(8);
        arvoreExemplo.inserir(30);
        arvoreExemplo.inserir(22);
        arvoreExemplo.inserir(35);
        arvoreExemplo.inserir(23);

        System.out.println(arvoreExemplo.procurarValor(25)); // true
        System.out.println(arvoreExemplo.procurarValor(70)); // false
        System.out.println("Árvore antes da remoção: ");
        arvoreExemplo.mostrarArvore();

        arvoreExemplo.remover(25);
        System.out.println("Árvore depois da remoção: ");
        arvoreExemplo.mostrarArvore();



    }
}
