import graph.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main {

    private static JPanel genControlLight(Graph graph)
    {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        JTextField textField = new JTextField(16);

        JButton btConnect = new JButton("connect");
        JButton btAddNode = new JButton("add Node");
        JButton btFindCycles = new JButton("Find Cycles");
        JTextArea consoleOut = new JTextArea ();
        JScrollPane scrollBar = new JScrollPane(consoleOut,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btAddNode.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                graph.add(250,250);
            }
        });
        btConnect.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String[] split = textField.getText().split(" ");
                if(split.length < 3)
                    return;

                graph.connect(split[0], split[1], Integer.parseInt(split[2]));
                textField.setText("");
            }
        });

        btFindCycles.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                graph.printNodes();

                graph.findCycles();
                graph.printCycles();
                System.out.println();

                graph.printCycles(graph.findMinCycles());
                System.out.println();

                graph.printCyclesLength(graph.findMinCycles());
                consoleOut.setText(baos.toString());
            }
        });




        System.out.println("hi");
        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(btConnect);
        panel.add(btAddNode);
        panel.add(btFindCycles);
        panel.setVisible(true);
        panel.add( scrollBar );

        return panel;
    }

    private static void genControlWindow(Graph graph)
    {
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(genControlLight(graph));
        frame.setVisible(true);
    }
    public static void main(String[] args) {


        JFrame frame = new JFrame("Task 2.3");

        // set the size of frame
        Graph graph = new Graph();
        genControlWindow(graph);
        frame.add(graph);
        frame.setSize(500, 500);

        frame.show();

    }
}
