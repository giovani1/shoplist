package hidden.challenge.shoplist.controller;

import hidden.challenge.shoplist.model.Shops;
import hidden.challenge.shoplist.service.ShopsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/ressource")
public class HomeController {

    private ShopsServiceImpl shopsServiceImpl;
    //to inject the bean shopsServiceImpl in constructor
    @Autowired
    public HomeController(ShopsServiceImpl shopsServiceImpl) {
        this.shopsServiceImpl = shopsServiceImpl;
    }

    //fetch all data,remove preferred and unwanted(under 2hours) shops from collection and send it to /ressource/
    @SuppressWarnings("unchecked")
    @RequestMapping("/")
    public Iterable<Shops> main(HttpSession httpSession){
        List<Shops> shops=shopsServiceImpl.list();

        List<Shops> likedShops=(List<Shops>) httpSession.getAttribute("LikedShops");
        Hashtable<Long,Shops> disLikedShops=(Hashtable<Long,Shops>) httpSession.getAttribute("DisLikedShops");
        if(likedShops==null){
            httpSession.setAttribute("LikedShops",new ArrayList<Shops>());
        }else{
            shops.removeAll(likedShops);
        }
        if(disLikedShops==null){
            httpSession.setAttribute("DisLikedShops",new Hashtable<Long,Shops>());
        }else {
            Long t=System.nanoTime();
            for (Enumeration<Long> e = disLikedShops.keys(); e.hasMoreElements();){
                Long ti=e.nextElement();
                if(TimeUnit.NANOSECONDS.toHours(t-ti)>2){
                    disLikedShops.remove(ti);
                }
            }
            shops.removeAll(disLikedShops.values());
        }

        return shops;
    }

    //add a shop to preferred list
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/like", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addToPreferred(@RequestBody String id,HttpSession httpSession){
        List<Shops> likedShops=(List<Shops>) httpSession.getAttribute("LikedShops");
        Shops s=shopsServiceImpl.findOne(id);
        if(!likedShops.contains(s))
            likedShops.add(s);
        httpSession.setAttribute("LikedShops",likedShops);
    }

    //add a shop to unwanted list
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/dislike", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addToDislike(@RequestBody String id,HttpSession httpSession){
        Hashtable<Long,Shops> disLikedShops=(Hashtable<Long,Shops>) httpSession.getAttribute("DisLikedShops");
        Shops s=shopsServiceImpl.findOne(id);
        if(!disLikedShops.contains(s))
            disLikedShops.put(System.nanoTime(),s);
        httpSession.setAttribute("DisLikedShops",disLikedShops);
    }

    //remove shops from preferred list
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/like", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removeFromPreferred(@RequestBody String id,HttpSession httpSession){
        List<Shops> likedShops=(List<Shops>) httpSession.getAttribute("LikedShops");
        likedShops.remove(shopsServiceImpl.findOne(id));
        httpSession.setAttribute("LikedShops",likedShops);
    }

    //send all preferred list
    @SuppressWarnings("unchecked")
    @RequestMapping("/like")
    public List<Shops> preferred(HttpSession httpSession){
        return (List<Shops>) httpSession.getAttribute("LikedShops");
    }



}
