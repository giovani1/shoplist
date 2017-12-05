package hidden.challenge.shoplist.controller;

import hidden.challenge.shoplist.model.Shops;
import hidden.challenge.shoplist.service.ShopsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ressource")
public class HomeController {

    private ShopsServiceImpl shopsServiceImpl;

    @Autowired
    public HomeController(ShopsServiceImpl shopsServiceImpl) {
        this.shopsServiceImpl = shopsServiceImpl;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/")
    public Iterable<Shops> main(HttpSession httpSession){
        List<Shops> shops=shopsServiceImpl.list();

        List<Shops> likedShops=(List<Shops>) httpSession.getAttribute("LikedShops");
        List<Shops> disLikedShops=(List<Shops>) httpSession.getAttribute("DisLikedShops");
        if(likedShops==null){
            httpSession.setAttribute("LikedShops",new ArrayList<Shops>());
        }else{
            shops.removeAll(likedShops);
        }
        if(disLikedShops==null){
            httpSession.setAttribute("DisLikedShops",new ArrayList<Shops>());
        }else {
            shops.removeAll(disLikedShops);
        }

        return shops;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/like", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addToPreferred(@RequestBody String id,HttpSession httpSession){
        List<Shops> likedShops=(List<Shops>) httpSession.getAttribute("LikedShops");
        Shops s=shopsServiceImpl.findOne(id);
        if(!likedShops.contains(s))
            System.out.println(likedShops.add(s));
        httpSession.setAttribute("LikedShops",likedShops);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/dislike", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void addToDislike(@RequestBody String id,HttpSession httpSession){
        List<Shops> disLikedShops=(List<Shops>) httpSession.getAttribute("DisLikedShops");
        Shops s=shopsServiceImpl.findOne(id);
        if(!disLikedShops.contains(s))
            System.out.println(disLikedShops.add(s));
        httpSession.setAttribute("DisLikedShops",disLikedShops);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/like", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void removeFromPreferred(@RequestBody String id,HttpSession httpSession){
        List<Shops> likedShops=(List<Shops>) httpSession.getAttribute("LikedShops");
        System.out.println(likedShops.remove(shopsServiceImpl.findOne(id)));
        httpSession.setAttribute("LikedShops",likedShops);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/like")
    public List<Shops> preferred(HttpSession httpSession){
        return (List<Shops>) httpSession.getAttribute("LikedShops");
    }



}
