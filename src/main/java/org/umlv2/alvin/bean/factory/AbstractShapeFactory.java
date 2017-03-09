package org.umlv2.alvin.bean.factory;

import java.util.Map;

import org.umlv2.alvin.bean.shape.data.BaseDataShape;
import org.w3c.dom.Node;

/**
 *
 */
public abstract class AbstractShapeFactory {

    public static AbstractShapeFactory createInstance(String type) {
        switch (type) {
            case "0":
                return new ActiveShapeFactory();
            case "1":
                return new UseCaseShapeFactory();
            case "2":
                return new SeriShapeFactory();
        }
        return null;
    }

    /**
     * @param type
     * @return
     */
    public abstract BaseDataShape createMenuShapeByType(String type);

    /**
     * @param type
     * @return
     */
    public abstract BaseDataShape createShapeFactory(String type);

    /**
     * @param node
     * @param map
     * @return
     */
    public abstract BaseDataShape toShape(Node node, Map<String, BaseDataShape> map);

}
