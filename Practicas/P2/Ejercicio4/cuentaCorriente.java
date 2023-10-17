public class cuentaCorriente {
    private double saldo;
    private long numCuenta;

    public cuentaCorriente(long numCuenta, double saldo){
        this.numCuenta = numCuenta;
        this.saldo = saldo;
    }

    public double verSaldo(){return saldo;}

    public void depositar(double s){saldo += s;}

    public void reintegro(double s){saldo -= s;}
}
