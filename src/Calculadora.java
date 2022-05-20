import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calculadora extends JFrame {
    private JButton buttonCalcular;
    private JTextField textFieldPrecio;
    private JTextField textFieldDescuento;
    private JTextField textFieldIVA;
    private JSpinner spinnerUnidades;
    private JLabel labelPrecioFinal;
    private JPanel pnl;

    private static final DecimalFormat df = new DecimalFormat("0.00");// para limitar el precio final a solo 2 decimales

    public Calculadora(String title) {//constructor
        super(title);//el titulo que voy a poner en el main
        pnl.setPreferredSize(new Dimension(400, 300));//tamano de la ventanita
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnl);
        this.pack();

        //ponemos el minimo value de 0 en el spinner
        spinnerUnidades.setModel(new SpinnerNumberModel(0, 0, 100, 1));

        //el boton calcular -- cuando lo pulsas
        buttonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {//manejamos excepciones

                    //guardamos los datos introducidos en variables que declaramos aqui
                    int noUnidades = (int) spinnerUnidades.getValue();
                    double precio = Double.parseDouble(textFieldPrecio.getText());
                    double descuento = Double.parseDouble(textFieldDescuento.getText());
                    if (descuento != 0)
                        descuento = descuento / 100;//calculo porcentaje
                    int IVA = Integer.parseInt(textFieldIVA.getText());
                    IVA = IVA / 100;//calculo porcentaje

                    //calculamos el precio final
                    double precioFinal = (precio - (precio * descuento) + (precio * IVA)) * noUnidades;
                    df.setRoundingMode(RoundingMode.DOWN);//esto es para formatear el precio final(solo 2 decimales)

                    //imprimo en el label el precio final
                    labelPrecioFinal.setText("Precio Final: €" + df.format(precioFinal));

                } catch (NumberFormatException ex) {//si el usuario no introduce bien los datos
                    JOptionPane.showMessageDialog(pnl,//ventana de dialogo
                            "Los datos introducidos no son validos!", // Texto del mensaje
                            "Error", // Título
                            JOptionPane.WARNING_MESSAGE);//icono
                }
            }
        });
    }

    //mi clase principal main
    public static void main(String[] args) {
        JFrame frame = new Calculadora("Calculadora");
        frame.setVisible(true);
    }
}
