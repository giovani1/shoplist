package hidden.challenge.shoplist.service;

import hidden.challenge.shoplist.model.Shops;
import hidden.challenge.shoplist.repositories.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopsServiceImpl implements ShopsService {
    private ShopsRepository shopsRepository;
    @Autowired
    public ShopsServiceImpl(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    public List<Shops> list(){
       //return shopsRepository.findAll();
        return shopsRepository.findByLocationNear(new Point(0,0),new Distance(Integer.MAX_VALUE, Metrics.KILOMETERS));
    }


    public Shops findOne(String id) {
        return shopsRepository.findOne(id);
    }
}
