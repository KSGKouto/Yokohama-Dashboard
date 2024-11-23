package yokohama.components;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.*;
import raven.drawer.component.menu.data.Item;
import raven.swing.AvatarIcon;
import raven.swing.blur.BlurBackground;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class SystemMenu extends BlurChild {

    public SystemMenu() {
        super(new Style()
                .setBlur(30)
                        .setBorder(new StyleBorder(10)
                                .setOpacity(0.15f)
                                .setBorderWidth(1.2f)
                                .setBorderColor(new GradientColor(new Color(200, 200,200), new Color(150,150,150),new Point2D.Float(0,0),new Point2D.Float(1f, 0)))
                        )
                .setOverlay(new StyleOverlay(new Color(0,0,0), 0.2f))
        );
      init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,fill", "[fill]", "[grow 0][fill]"));
        simpleMenu = new SimpleMenu(getMenuOption());
        simpleMenu.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(simpleMenu);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:999;" +
                "width:5;" +
                "thumbInsets:0,0,0,0");

        // header
        SimpleHeader header = new SimpleHeader(getHeaderData());
        header.setOpaque(false);
        add(header);
        add(scrollPane);
    }

    private SimpleHeaderData getHeaderData() {
        return new SimpleHeaderData()
                .setTitle("Yokohama")
                .setDescription("Yokohama Seguros")
                .setIcon(new AvatarIcon(getClass().getResource("/yokohama/images/profile.png"), 60, 60, 999));
    }

    private SimpleMenuOption getMenuOption() {
        raven.drawer.component.menu.data.MenuItem items[] = new raven.drawer.component.menu.data.MenuItem[] {
                new Item.Label("Corretores"),
                new Item("Dashboard Corretores", "dashboard.svg")
                        .subMenu("Carteira de clientes")
                        .subMenu("Status de solicitações")
                        .subMenu("Simulação de seguro pesonalizado"),
                new Item.Label("Clientes"),
                new Item("Dashboard Clientes", "dashboard.svg")
                        .subMenu("Faturas")
                        .subMenu("Relatorio seguro")
                        .subMenu("Acionar seguro")
                        .subMenu("Editar dados dos carros")
                        .subMenu("Atualizar informações")
                        .subMenu("Simular custo do seguro")
                        .subMenu("Opções de Seguros")
                        .subMenu("Corretorres mais proximo"),

        };
        return new SimpleMenuOption()
                .setBaseIconPath("yokohama/menu")
                .setIconScale(0.5f)
                .setMenus(items)
                .setMenuStyle(new SimpleMenuStyle() {
                    @Override
                    public void styleMenuPanel(JPanel panel, int[] index) {
                        panel.setOpaque(false);
                    }

                    @Override
                    public void styleMenuItem(JButton menu, int[] index) {
                        menu.setContentAreaFilled(false);
                    }
                })
        .addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction menuAction, int[] ints) {
                System.out.println("Menu Select:");
                if (ints.length==1){
                    int index=ints[0];
                    if (index==0) {
                        FormManager.getInstance().showForm("Dashboard Title", new JLabel("Dashboard",SwingConstants.CENTER));
                    }
                }else if (ints.length==2){
                    int index=ints[0];
                    int subIndex=ints[1];
                    if (index==1){
                        if (subIndex==1){
                            FormManager.getInstance().showForm("Dashboard Title", new JLabel("Faturas",SwingConstants.CENTER));
                        }else if (subIndex==1){
                            FormManager.getInstance().showForm("Dashboard Title", new JLabel("Relatorios Seguros",SwingConstants.CENTER));
                        }
                    }
                }
            }
        });
    }

    private SimpleMenu simpleMenu;
}
