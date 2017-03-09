package org.umlv2.alvin.ui.comp;

import java.awt.BorderLayout;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.umlv2.alvin.bean.shape.ShapeHelper;
import org.umlv2.alvin.core.UMLCore;
import org.umlv2.alvin.sys.Application;
import org.umlv2.alvin.ui.drawpane.DrawPane;

public class JTitlePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public static JTitlePanel createInstance(String type, DrawPane drawPane) {
        JTitlePanel panel = new JTitlePanel();
        switch (type) {
            case "0":
                panel.changeToActivity(drawPane);
                break;
            case "1":
                panel.changeToUsercase(drawPane);
                break;
            case "2":
                panel.changeToSeri(drawPane);
                break;
        }
        return panel;
    }

    private JLabel titleButton;
    private JPanel contentPanel;
    private String type;

    private JTitlePanel() {
        titleButton = new JLabel();
        titleButton.setHorizontalAlignment(JLabel.CENTER);
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        setLayout(new BorderLayout(0, 5));
        add(BorderLayout.NORTH, titleButton);
        add(contentPanel);
    }

    public void changeType(String type) throws Exception {
        if (type.equals(this.type)) {
            return;
        }
        this.type = type;
        contentPanel.removeAll();
        System.gc();
        Class<?> clzz = this.getClass();
        Method me = clzz.getDeclaredMethod(type);
        me.invoke(this);
        updateUI();
        Application.saveType = type;
    }

    private void changeToActivity(DrawPane drawPanel) {
        titleButton.setText("Activity");
        changeShapeButtons(ShapeHelper.getActiveTypeList(), drawPanel);
        Application.setSaveType("changeToActivity");
    }

    private void changeToSeri(DrawPane drawPanel) {
        titleButton.setText("Seri");
        changeShapeButtons(ShapeHelper.getSeriTypeList(), drawPanel);
        Application.setSaveType("changeToSeri");
    }

    private void changeToUsercase(DrawPane drawPanel) {
        titleButton.setText("UseCase");
        changeShapeButtons(ShapeHelper.getUseCaseTypeList(), drawPanel);
        Application.setSaveType("changeToUsercase");
    }

    private void changeShapeButtons(List<String> list, DrawPane drawPanel) {
        int x = 0;
        int y = 0;
        int size = 80;
        for (int i = 0; i < list.size(); i++) {
            x = i % 2 * size + 10;
            if (i % 2 == 1) {
                x += 5;
            }
            ShapeButton button = new ShapeButton(drawPanel, list.get(i));
            button.setBounds(x, y, size, size);

            contentPanel.add(button);
            if (i % 2 == 1) {
                y += size + 5;
            }
            UMLCore.getMainFrame().getDragManager().canDrag(button, drawPanel);
        }
    }

}
