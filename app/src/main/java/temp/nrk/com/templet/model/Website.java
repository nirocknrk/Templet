package temp.nrk.com.templet.model;


import temp.nrk.com.templet.db.entity.WebsiteEntity;

public class Website {
    public String id_website;
    public String website_name;
    public String visit_date;
    public String total_visits;

    public WebsiteEntity toWebsiteEntity(){
        int toalVisit = 0;
        try{
            toalVisit = Integer.parseInt(total_visits);
        }catch (NumberFormatException e) {}

        return new WebsiteEntity(id_website,website_name,visit_date,toalVisit);
    }
}
