package managedbean;
import java.util.List;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import model.Avi;

@ManagedBean(name="AviBean")
@SessionScoped
public class AviBean implements Serializable{
private static final long serialVersionUID = 1L;
	
	private String clientId;
	private String comm ;
	private String date ;
	

	private int score;
	private String reponse;
	private List<Avi> Avis;
	
	//UserBean ub= new UserBean();
	//LoginBean lb = new LoginBean();
	private Integer AviIdToBeUpdated;
	
	

	public AviBean() {
		super();
	}

	@EJB
	service.AviService AviService;
	public AviBean(String clientId, String comm, String date, int score, String reponse) {
		super();
		this.clientId = clientId;
		this.comm = comm;
		this.date = date;
		this.score = score;
		this.reponse = reponse;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public Integer getAviIdToBeUpdated() {
		return AviIdToBeUpdated;
	}

	public void setAviIdToBeUpdated(Integer AviIdToBeUpdated) {
		this.AviIdToBeUpdated = AviIdToBeUpdated;
	}
	
	
	
	
	
	
	
	public String addAvi() {
		
		
				AviService.AddAvi(new Avi(clientId,comm,date,reponse,score));
				
				return "/pages/afficheAvis?faces-redirect=true";
		
			}
		
		
		
		public void removeAvi(int AviId)
		{ AviService.deleteAviById(AviId);
		}
		
		
		public String modifier(Avi Avi){
			String navigateto=null;
			navigateto="/pages/AjoutAvis?faces-redirect=true";
			this.setClientId(Avi.getClientId());
			this.setComm(Avi.getComm());
		
			this.setDate(Avi.getDate());
			this.setScore(Avi.getScore());
			this.setReponse(Avi.getReponse());
           return navigateto ; 
		}
		
		public void mettreAjourAvi(){
			AviService.updateAvi(new Avi( clientId,comm,
					date, reponse, score));
		}

		public List<Avi> getAvis() {
			Avis = AviService.getAllAvi();
			return Avis;
		}

		public void setAvis(List<Avi> avis) {
			Avis = avis;
		}
		
	
}
