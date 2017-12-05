package hidden.challenge.shoplist.repositories;

import hidden.challenge.shoplist.model.Shops;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
/**
 *@author mohammed choubby
 * shops repository interface
 *
 */
public interface ShopsRepository extends MongoRepository<Shops, String> {
    //fetch all objects ordered by location
    List<Shops> findByLocationNear(Point point, Distance distance);
    //fetch one object by id
    Shops findOne(String id);
}
