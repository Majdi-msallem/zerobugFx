/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import java.util.List;
import pidev.Entity.User;

/**
 *
 * @author majdi
 */
public interface IserviceUser<T> {
      public void AddUser( T t);
    public List<T> AffUser();
    public void modifier(T t);
      public void supprimer(T t);
}
