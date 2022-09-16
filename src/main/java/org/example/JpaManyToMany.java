package org.example;

import jakarta.persistence.EntityManager;
import org.example.configuration.JpaUtilDb;
import org.example.entity.Alumno;
import org.example.entity.Curso;

public class JpaManyToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        try {
            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Jose","Perez");
            Alumno alumno2 = new Alumno("Juanjo","Rodriguez");

            Curso curso1 = new Curso("Java", "George");
            curso1.getAlumnos().add(alumno1);
            curso1.getAlumnos().add(alumno2);
            Curso curso2 = new Curso("React", "Maxi");
            curso2.getAlumnos().add(alumno1);

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            em.persist(alumno1);
            em.persist(alumno2);

            em.getTransaction().commit();
            System.out.println("Curso 1: " + curso1);
            System.out.println("Curso 2: " + curso2);

            em.getTransaction().begin();
            Alumno findAlumno1 = em.find(Alumno.class, 1L);
            Alumno findAlumno2 = em.find(Alumno.class, 2L);

            System.out.println("ALUMNO 1: "+ findAlumno1);
            System.out.println("ALUMNO 2: "+ findAlumno2);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
