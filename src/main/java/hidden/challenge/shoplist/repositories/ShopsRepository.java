package hidden.challenge.shoplist.repositories;

import hidden.challenge.shoplist.model.Shops;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShopsRepository extends MongoRepository<Shops, String> {
    //List<Shops> findAll();
    List<Shops> findByLocationNear(Point point, Distance distance);
    Shops findOne(String id);
}
