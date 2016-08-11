/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 *
 * @author epam
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentJDBCDAO student = new StudentJDBCDAO();
		StudentBean alok = new StudentBean();
		alok.setName("JavaMan");
		alok.setId(5);
		alok.setCourse("4");
		alok.setAddress("Kudryashova");
		StudentBean tinkoo = new StudentBean();
		tinkoo.setName("Pupkin");
		tinkoo.setId(3);
		// Adding Data
		student.add(alok);
		// Deleting Data
//		student.delete(7);
		// Updating Data
//		student.update(tinkoo);
		// Displaying Data
		student.findAll();
    }

}

