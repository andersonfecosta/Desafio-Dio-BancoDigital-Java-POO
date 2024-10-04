import entities.Banco;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Banco banco = Banco.getInstance();
        Menu menu = new Menu();
        menu.iniciar();
    }
}