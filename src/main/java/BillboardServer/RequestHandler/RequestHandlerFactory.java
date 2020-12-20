package BillboardServer.RequestHandler;

import BillboardServer.RequestHandler.Handlers.*;
import Commmunication.Requests.*;


public class RequestHandlerFactory {
    public static RequestHandler getMessageHandler(String messageType){
        if(messageType == null) {
            return null;
        }
        if (messageType.equals(LoginRequest.class.getSimpleName())){
            return new LoginRequestHandler();
        }
        if (messageType.equals(CheckTokenRequest.class.getSimpleName())){
            return new CheckTokenRequestHandler();
        }
        if(messageType.equals(GetAllUsersRequest.class.getSimpleName())){
            return new GetAllUsersRequestHandler();
        }
        if(messageType.equals(EditUserRequest.class.getSimpleName())){
            return new EditUserRequestHandler();
        }
        if (messageType.equals(LogoutRequest.class.getSimpleName())) {
            return new LogoutRequestHandler();
        }
        if (messageType.equals(CreateBillboardRequest.class.getSimpleName())) {
            return new CreateBillboardRequestHandler();
        }
        if(messageType.equals(GetAllBillboardRequest.class.getSimpleName())){
            return new GetAllBillboardRequestHandler();
        }
        if(messageType.equals(DeleteUserRequest.class.getSimpleName())){
            return new DeleteUserRequestHandler();
        }
        if (messageType.equals(EditBillboardRequest.class.getSimpleName())) {
            return new EditBillboardRequestHandler();
        }
        if (messageType.equals(DeleteBillboardRequest.class.getSimpleName())) {
            return new DeleteBillboardRequestHandler();
        }
        if (messageType.equals(GetAllScheduleRequest.class.getSimpleName())) {
            return new GetAllScheduleRequestHandler();
        }
        if (messageType.equals(CreateScheduleRequest.class.getSimpleName())) {
            return new CreateScheduleRequestHandler();
        }
        if (messageType.equals(EditScheduleRequest.class.getSimpleName())) {
            return new EditScheduleRequestHandler();
        }
        if (messageType.equals(DeleteScheduleRequest.class.getSimpleName())) {
            return new DeleteScheduleRequestHandler();
        }
        if (messageType.equals(CurrentBillboardRequest.class.getSimpleName())) {
            return new CurrentBillboardRequestHandler();
        }
        if (messageType.equals(EditUserNoPassRequest.class.getSimpleName())) {
            return new EditUserNoPassHandler();
        }
        if (messageType.equals(CreateUserRequest.class.getSimpleName())) {
            return new CreateUserRequestHandler();
        } else {
            return null;
        }
    }
}