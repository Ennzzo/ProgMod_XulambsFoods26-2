public enum EBorda {
    CHEDDAR(10),
    CHOCOLATE(8),
    REQUEIJAO(7),
    TRADICIONAL(0);

    double valor;
    EBorda(double preco){
        valor = preco;
    }
    public double getValor(){
        return  valor;
    }
}
