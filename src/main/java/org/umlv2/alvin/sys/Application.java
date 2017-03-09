package org.umlv2.alvin.sys;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.UUID;

import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.umlv2.alvin.ui.comp.JTitlePanel;
import org.umlv2.alvin.core.UMLCore;

/**
 * 应用级别的类
 *
 * @author 唐植超
 *
 */
public class Application {

    public static void setSaveType(String type) {
        saveType = type;
//        org.umlv2.alvin.action.ActionManager.fireActionEnabled();
    }

    private Application() {

    }

    // 操作类型
    public static final String OPER_NONE = "none";
    public static final String OPER_DRAWLINE = "drawline";
    public static final String OPER_DRAWSELECTBOX = "drawselectbox";
    public static final String OPER_DRAGSHAPE = "drag";
    public static final String OPER_RESIZE = "resize";
    public static final String OPER_SELECTALL = "selectAll";
    public static String OPER_MUILT_SELECT = "muilt_select";
//	// 图形拖动管理
//	public static ShapeDragManager dragManager;

    // 住窗体控件
//	public static JFrame mainFrame;
//	// 画图板
//	public static DrawPane drawPanel;
    // 工具栏
    public static JPanel toolbarBox;
    // 左边的工具箱
    public static JTitlePanel shapePanel;
    // 底部的状态条
    public static Box statusBox;
    // 上面的菜单条
    public static JMenuBar menuBar;
    // 主操作面板
    public static JSplitPane mainSplitPanel;
    // 中心的面板
    public static JPanel contentPane;
    // 打开时，默认面板
    public static JPanel defaultPanel;
    // 保存类型
    public static String saveType;
    // 锚点
    public static Point2D anchor = new Point2D.Double(-1, -1);
    // 选择框
    public static Rectangle2D selectBox = new Rectangle2D.Double(-1, -1, -1, -1);
    // 内存图片
//	public static BufferedImage image = new BufferedImage(600, 600,
//			BufferedImage.TYPE_3BYTE_BGR);
    // 画图对象
//	public static Graphics2D g2d = image.createGraphics();
    // 比率调整的选择框
//    public static ScaleCombbox scaleCombox;
//    // 画图处理
//    public static BaseDrawPanelBiz drawPanelBiz;
//    // 拖动处理
//    public static BaseDragBiz dragBiz;
//    // 图形生产的抽象工厂
//    public static AbstractShapeFactory shapeFactory;

    // 生成唯一ID
    public static String getTimeId() {
        return UUID.randomUUID().toString();
    }

    public static void repaint() {
        UMLCore.getMainFrame().getDrawPane().reDraw();
        UMLCore.getMainFrame().getDrawPane().repaint();
        org.umlv2.alvin.action.ActionManager.fireActionEnabled();
    }

    public static void selectAllShape() {
        for (int i = UMLCore.getMainFrame().getDrawPane().getShapes().size() - 1; i >= 0; i--) {
            BaseDataShape shape = UMLCore.getMainFrame().getDrawPane().getShapes().get(i);
            shape.setSelect(true);
        }
        UMLCore.getMainFrame().getDrawPane().setCurrentSelectShape(null);
        repaint();
    }

//    public static void start(StartWindow s) {
//		// 实例化画板
//		s.putProcessString(Language.getValue("Init Drawpanel"));
//		Application.drawPanel = new DrawPane();
//		// 加载剪贴板和撤销恢复
//		s.putProcessString(Language.getValue("Init ClipBoard"));
//		SMMSystem.drawPaneClipBoard = new DrawPaneClipboard();
//		s.putProcessString(Language.getValue("Init Undo Redo manager"));
//		SMMSystem.undoManager = new UndoableEditManager();
//		s.setValue(35);
//		// 实例化窗体
//		s.putProcessString(Language.getValue("Init Frame"));
//		Application.mainFrame = new MainFrame();
//		s.setValue(40);
//		// 实例化工具条
//		s.putProcessString(Language.getValue("Init Toolbar"));
//		ToolbarManager.createToolbar();
//		// 实例化状态条
//		s.putProcessString(Language.getValue("Init StatusBar"));
//		StatusbarManager.createStatusBar();
//		s.setValue(55);
//		// 实例化左边的菜单
//		s.putProcessString(Language.getValue("Init Shape panel"));
//		Application.dragManager = new ShapeDragManager(Application.mainFrame,
//				Application.drawPanel);
//		Application.shapePanel = new JTitlePanel();
//		s.setValue(70);
//		// 实例化菜单
//		s.putProcessString(Language.getValue("Init Action"));
//		MenuBarManager.createMenuBar();
//		ActionManager.createPopup();
//		s.setValue(85);
//		// 界面信息实例化
//		s.putProcessString(Language.getValue("Init GUI"));
//		CenterPanelManager.createContentPanel();
//		((MainFrame) Application.mainFrame).initGui();
//		s.setValue(100);
//		s.stopSplash();
//		Application.mainFrame.setVisible(true);
//		s = null;
//		LogUtil.info("界面启动成功");
//
//		Application.mainFrame.addWindowListener(new WindowAdapter() {
//			
//			public void windowClosing(WindowEvent e) {
//				ActionManager.getAction(ExitAction.class).actionPerformed(null);
//			}
//		});
//    }
}
