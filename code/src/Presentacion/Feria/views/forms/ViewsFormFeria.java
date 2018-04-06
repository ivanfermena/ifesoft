package Presentacion.Feria.views.forms;

import Negocio.Feria.Tferia;
import Presentacion.Feria.views.events.Event;
import Presentacion.Feria.UIimp;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ViewsFormFeria extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextField textFieldNombre;
    private JPanel contentPanel3;
    private JLabel label2;
    private JTextField textFieldDescripcion;
    private JPanel contentPanel4;
    private JLabel label3;
    private JTextField textFieldFechaIni;
    private JPanel contentPanel5;
    private JLabel label4;
    private JTextField textFieldFechaFin;
    private JPanel buttonBar;
    private JButton createButtonForm;
    private JButton cancelButton;
    private JButton helpButton;

    private Tferia tferiaModify;
    private boolean controlOption;

    public ViewsFormFeria() {
        initComponents();
        controlOption = true;
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewsFormFeria(Tferia tferia) {
        initComponents();

        controlOption = false;
        this.tferiaModify = tferia;

        initComponentsModify();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void initComponentsModify() {
        textFieldNombre.setText(tferiaModify.getName());
        textFieldDescripcion.setText(tferiaModify.getDescription());
        textFieldFechaIni.setText(String.valueOf(tferiaModify.getIniDate()));
        textFieldFechaFin.setText(String.valueOf(tferiaModify.getEndDate()));
    }

    private void createButtonFormActionPerformed() {
        this.setVisible(false);
        String name = textFieldNombre.getText();
        String descrip = textFieldNombre.getText();
        String a_fecha_ini = textFieldFechaIni.getText();
        String m_fecha_ini = textFieldFechaFin.getText();
        String d_fecha_ini = textFieldFechaFin.getText();
        String fecha_fin = textFieldFechaFin.getText();

        Tferia tFeria = new Tferia(name, descrip, new Date(Integer.parseInt(a_fecha_ini), Integer.parseInt(m_fecha_ini), Integer.parseInt(d_fecha_ini)), new Date(Integer.parseInt(fecha_fin)));

        if (controlOption)  UIimp.getInstance().execute(Event.INSERT_FERIA, tFeria);
        else UIimp.getInstance().execute(Event.MODIFY_FERIA, tFeria);
    }

    private void cancelButtonStateChanged() {
        this.setVisible(false);
        UIimp.getInstance().execute(Event.CREATE_HALF, null);
    }

    private void helpButtonActionPerformed() {

    }

    private void initComponents() {
        dialogPanel = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        label1 = new JLabel();
        textFieldNombre = new JTextField();
        contentPanel3 = new JPanel();
        label2 = new JLabel();
        textFieldDescripcion = new JTextField();
        contentPanel4 = new JPanel();
        label3 = new JLabel();
        textFieldFechaIni = new JTextField();
        contentPanel5 = new JPanel();
        label4 = new JLabel();
        textFieldFechaFin = new JTextField();
        buttonBar = new JPanel();
        createButtonForm = new JButton();
        cancelButton = new JButton();
        helpButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPanel ========
        {
            dialogPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPanel.getBorder())); dialogPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPanel.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                    //---- label1 ----
                    label1.setText("Nombre");
                    contentPanel2.add(label1);
                    contentPanel2.add(textFieldNombre);
                }
                contentPanel.add(contentPanel2);

                //======== contentPanel3 ========
                {
                    contentPanel3.setLayout(new BoxLayout(contentPanel3, BoxLayout.X_AXIS));

                    //---- label2 ----
                    label2.setText("Descripcion");
                    contentPanel3.add(label2);
                    contentPanel3.add(textFieldDescripcion);
                }
                contentPanel.add(contentPanel3);

                //======== contentPanel4 ========
                {
                    contentPanel4.setLayout(new BoxLayout(contentPanel4, BoxLayout.X_AXIS));

                    //---- label3 ----
                    label3.setText("Fecha inicio");
                    contentPanel4.add(label3);
                    contentPanel4.add(textFieldFechaIni);
                }
                contentPanel.add(contentPanel4);

                //======== contentPanel5 ========
                {
                    contentPanel5.setLayout(new BoxLayout(contentPanel5, BoxLayout.X_AXIS));

                    //---- label4 ----
                    label4.setText("Fecha fin");
                    contentPanel5.add(label4);
                    contentPanel5.add(textFieldFechaFin);
                }
                contentPanel.add(contentPanel5);
            }
            dialogPanel.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- createButtonForm ----
                createButtonForm.setText("Add");
                createButtonForm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createButtonFormActionPerformed();
                    }
                });
                buttonBar.add(createButtonForm, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelButtonStateChanged();
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- helpButton ----
                helpButton.setText("Help");
                helpButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        helpButtonActionPerformed();
                    }
                });
                buttonBar.add(helpButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPanel.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}