package matteopasina.services;

import matteopasina.model.request.Request;
import matteopasina.model.response.Response;


public interface IAuthService {
    Response call(Request request);
}
