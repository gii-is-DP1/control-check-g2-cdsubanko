package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FeedingController {
    private final FeedingService feedingService;

    @Autowired
    public FeedingController(FeedingService feed){
        this.feedingService = feed;
    }

    /*
    @ModelAttribute("feedingTypes")
    public List<FeedingType> loadFeedingTypes() {
    List<FeedingType> pTypes = this.feedingService.getAllFeedingTypes();
    return pTypes;
    }*/

    @GetMapping(value = "/feeding/create")
    public String initNewProductForm(ModelMap model) {
    Feeding feeding = new Feeding();
    model.put("feeding", feeding);
    return "feedings/createOrUpdateFeedingForm";
    }


    
    @PostMapping(value = "/feeding/create")
    public String processNewProductForm(@Valid Feeding feeding, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "feedings/createOrUpdateFeedingForm";
        }
        else {
            try{
                this.feedingService.save(feeding);
            }
            catch(UnfeasibleFeedingException e){
                
                return "feedings/createOrUpdateFeedingForm";
            }
            return "redirect:/welcome";
        }
    }
    
}

    

