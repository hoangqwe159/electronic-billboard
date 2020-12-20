package Commmunication;

import Commmunication.Requests.*;
import Commmunication.Response.*;

/**
 * Generate message based on message type
 */
public class MessageFactory {
    public static Message getMessage(String messageType){
        //TODO implement the factory for create message
        if(messageType == null) {
            return null;
        }
        if (messageType.equals(LoginRequest.class.getSimpleName())){
            return new LoginRequest();
        }
        if(messageType.equals(CreateBillboardRequest.class.getSimpleName())){
            return new CreateBillboardRequest();
        }
        if(messageType.equals(CreateBillboardResponse.class.getSimpleName())){
            return new CreateBillboardResponse();
        }
        if (messageType.equals(CreateUserResponse.class.getSimpleName())) {
            return new CreateUserResponse();
        }
        if(messageType.equals(LoginResponse.class.getSimpleName())){
            return new LoginResponse();
        }
        if (messageType.equals(CheckTokenRequest.class.getSimpleName())){
            return new CheckTokenRequest();
        }
        if(messageType.equals(CheckTokenResponse.class.getSimpleName())){
            return new CheckTokenResponse();
        }
        if(messageType.equals(GetAllUsersRequest.class.getSimpleName())){
            return new GetAllUsersRequest();
        }
        if(messageType.equals(GetAllUsersResponse.class.getSimpleName())){
            return new GetAllUsersResponse();
        }
        if(messageType.equals(EditUserRequest.class.getSimpleName())){
            return new EditUserRequest();
        }
        if(messageType.equals(EditUserResponse.class.getSimpleName())){
            return new EditUserResponse();
        }
        if (messageType.equals(CreateUserRequest.class.getSimpleName())) {
            return new CreateUserRequest();
        }
        if (messageType.equals(CreateUserResponse.class.getSimpleName())) {
            return new CreateUserResponse();
        }
        if(messageType.equals(GetAllBillboardRequest.class.getSimpleName())){
            return new GetAllBillboardRequest();
        }
        if(messageType.equals(GetAllBillboardResponse.class.getSimpleName())){
            return new GetAllBillboardResponse();
        }
        if(messageType.equals(DeleteUserRequest.class.getSimpleName())){
            return new DeleteUserRequest();
        }
        if (messageType.equals(DeleteUserResponse.class.getSimpleName())){
            return new DeleteUserResponse();
        }
        if (messageType.equals(EditBillboardRequest.class.getSimpleName())) {
            return new EditBillboardRequest();
        }
        if (messageType.equals(EditBillboardResponse.class.getSimpleName())) {
            return new EditBillboardResponse();
        }
        if(messageType.equals(DeleteBillboardRequest.class.getSimpleName())){
            return new DeleteBillboardRequest();
        }
        if (messageType.equals(DeleteBillboardResponse.class.getSimpleName())) {
            return new DeleteBillboardResponse();
        }
        if (messageType.equals(GetAllScheduleRequest.class.getSimpleName())) {
            return new GetAllScheduleRequest();
        }
        if (messageType.equals(GetAllScheduleResponse.class.getSimpleName())) {
            return new GetAllScheduleResponse();
        }
        if (messageType.equals(CreateScheduleRequest.class.getSimpleName())) {
            return new CreateScheduleRequest();
        }
        if (messageType.equals(CreateScheduleResponse.class.getSimpleName())) {
            return new CreateScheduleResponse();
        }
        if (messageType.equals(EditScheduleRequest.class.getSimpleName())) {
            return new EditScheduleRequest();
        }
        if (messageType.equals(EditScheduleResponse.class.getSimpleName())) {
            return new EditScheduleResponse();
        }
        if (messageType.equals(DeleteScheduleRequest.class.getSimpleName())) {
            return new DeleteScheduleRequest();
        }
        if (messageType.equals(DeleteScheduleResponse.class.getSimpleName())) {
            return new DeleteScheduleResponse();
        }
        if(messageType.equals(EndResponse.class.getSimpleName())){
            return new EndResponse();
        }
        if (messageType.equals(CurrentBillboardRequest.class.getSimpleName())) {
            return new CurrentBillboardRequest();
        }
        if (messageType.equals(CurrentBillboardResponse.class.getSimpleName())) {
            return new CurrentBillboardResponse();
        }
        if (messageType.equals(EditUserNoPassRequest.class.getSimpleName())) {
            return new EditUserNoPassRequest();
        }
        if (messageType.equals(LogoutRequest.class.getSimpleName())) {
            return new LogoutRequest();
        } else {
            return null;
        }
    }
}
