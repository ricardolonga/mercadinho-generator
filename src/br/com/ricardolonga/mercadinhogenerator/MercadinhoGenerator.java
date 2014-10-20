
package br.com.ricardolonga.mercadinhogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MercadinhoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(3, "br.com.ricardolonga.mercadinho");

        Entity categoria = schema.addEntity("Categoria");
        categoria.addIdProperty().autoincrement();
        categoria.addStringProperty("nome").notNull();
        categoria.implementsSerializable();

        Entity item = schema.addEntity("Item");
        item.addIdProperty().autoincrement();
        item.addStringProperty("nome").notNull();
        item.addIntProperty("quantidade");
        item.addDoubleProperty("valorUnitario");
        item.addDoubleProperty("valorTotal");
        item.implementsSerializable();
        Property idCategoria = item.addLongProperty("idCategoria").notNull().getProperty();

        item.addToOne(categoria, idCategoria);
        categoria.addToMany(item, idCategoria, "itens");

        new DaoGenerator().generateAll(schema, "../mercadinho/src-gen");
    }

}
