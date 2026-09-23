import java.util.LinkedList;
import java.util.List;

public class XulambsApp {
    private List<Pedido> listaPedidos;

    void limparTela() {
        IO.print("\033[H\033[2J");
    }

    void pausa(){
        IO.readln("Digite <ENTER> para continuar");
        limparTela();
    }

    int lerNumero(String mensagem){
        return Integer.parseInt(IO.readln(mensagem));
    }

    void config(){
        listaPedidos = new LinkedList<>();

    }

    void cabecalho(){
        limparTela();
        IO.println("XULAMBS PIZZA - v0.2");
        IO.println("=====================");
        IO.println("Pizzas vendidas hoje: " +
                    Pizza.getPizzasVendidas());
    }

    int exibirMenu() {
        cabecalho();
        IO.println("1 - Abrir pedido");
        IO.println("2 - Alterar pedido");
        IO.println("3 - Relatório de um pedido");
        IO.println("4 - Encerrar pedido");
        IO.println("0 - Sair");
        return lerNumero("Digite sua opção: ");
    }

    Pizza comprarPizza(){
        cabecalho();
        int adicionais = lerNumero("Quantos ingredientes? ");
    
        Pizza nova = new Pizza();
        nova.adicionarIngredientes(adicionais);

        mostrarNota(nova);
        return nova;
    }

    void mostrarNota(Pizza pizza){
        IO.println("Pizza comprada:");
        IO.println(pizza.gerarCupom());
        IO.println("=====================");
    }

    void armazenarPedido(Pedido pedido){
        if(pedido != null)
            listaPedidos.add(pedido);
    }

    void abrirPedido(){
        Pedido novoPedido = new Pedido();
        String novaPizza;
        do {
            Pizza pizza = comprarPizza();
            novoPedido.adicionarPizza(pizza);
            novaPizza = IO.readln("Mais pizza? (s/n)");
        } while (novaPizza.equals("s"));
        exibirRelatorio(novoPedido);
        armazenarPedido(novoPedido);
    }

    void alterarPedido(){
        Pedido buscado = localizarPedido();
        if(buscado != null){
            Pizza pizza = comprarPizza();
            buscado.adicionarPizza(pizza);
            exibirRelatorio(buscado);
        }
    }

    void relatorioPedido(){
        Pedido buscado = localizarPedido();
        if(buscado != null){
            exibirRelatorio(buscado);
        }
    }

     void encerrarPedido(){
        Pedido buscado = localizarPedido();
        if(buscado != null){
            buscado.fecharPedido();
            exibirRelatorio(buscado);
        }
    }

    Pedido localizarPedido(){
        cabecalho();
        IO.println("LOCALIZAÇÃO DE PEDIDOS\n");
        int codigo = lerNumero("Código do pedido: ");
        Pedido localizado = null;
        for (int i = 0; i < listaPedidos.size() && localizado == null; i++) {
            Pedido candidato = listaPedidos.get(i);
            if(candidato.getID() == codigo){
                localizado = candidato;
            }
        }
        return localizado;            
    }

    void exibirRelatorio(Pedido pedido){
        cabecalho();
        IO.println("RELATÓRIO DE PEDIDO\n");
        String mensagem = "Pedido não encontrado";
        if(pedido != null)
            mensagem = pedido.relatorio();
       
        IO.println(mensagem);
    }

    void main(){
        int opcao;
        config();
        do {
            opcao = exibirMenu();
            switch (opcao) {
                case 1 -> abrirPedido();
                case 2 -> alterarPedido();
                case 3 -> relatorioPedido();
                case 4 -> encerrarPedido();
                case 0 -> IO.println("Encerrando!");
                default -> IO.println("Opção inválida");
            }   
            pausa(); 
        } while (opcao != 0);
    }
}
