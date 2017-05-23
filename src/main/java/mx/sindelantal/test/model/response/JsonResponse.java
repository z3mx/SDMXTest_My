package mx.sindelantal.test.model.response;

import java.util.List;

import org.springframework.http.HttpStatus;

public class JsonResponse {
    private HttpStatus status;
    private int statusCode; 
    private String statusReason;
    private String binaryStatus;
    private String message;
    private List<?> result;
    
    public void setStatus(HttpStatus status){
        this.status = status;
    }
    public HttpStatus getStatus(){
        return status;
    }
    
    public void setStatusReason(String reason){
        this.statusReason = reason;
    }
    public String getStatusReason(){
    	if(status != null){
    		return status.getReasonPhrase();
    	}
        return statusReason;
    }
    
    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }
    public int getStatusCode(){
    	if(status != null){
    		return status.value();
    	}
        return statusCode;
    }
    
    public void setBinaryStatus(String binaryStatus){
        this.binaryStatus = binaryStatus;
    }
    public String getBinaryStatus(){
        return binaryStatus;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public void setResult(List<?> result){
    	this.result = result;
    }
    public List<?> getResult(){
    	return result;
    }
    
}
