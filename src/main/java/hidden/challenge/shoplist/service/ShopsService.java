package hidden.challenge.shoplist.service;

import hidden.challenge.shoplist.model.Shops;

public interface ShopsService {
     Iterable<Shops> list();
    Shops findOne(String id);
}
