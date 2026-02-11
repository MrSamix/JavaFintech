package org.example;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var session = HibernateHelper.getSession();
        String input = "";
        while (!input.equals("0"))
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("MENU: \n[1] Add new category \n[2] Remove category \n[3] Update category \n[4] Print list of categories \n[0] Exit \nPlease, input a number with your choice: ");
            input = scanner.nextLine();
            System.out.println();
            switch (input) {
                case "1":
                    System.out.println("Enter category name: ");
                    var categoryName = scanner.nextLine();
                    session.beginTransaction();
                    CategoryEntity category = new CategoryEntity(categoryName);

                    // Додати нову категорію
                    session.persist(category);

                    // Зафіксувати/зберегти зміни
                    session.getTransaction().commit();
                    System.out.println("Saved!");
                    break;
                case "2":
                    PrintCategories(session);
                    System.out.println("Enter a id category, which you want to delete:");
                    String select = scanner.nextLine();
                    int id;
                    try {
                        id = Integer.parseInt(select);
                    } catch (NumberFormatException e) {
                        System.out.println("Невірний формат ID!");
                        return;
                    }

                    CategoryEntity categoryDel = session.find(CategoryEntity.class, id);

                    if (categoryDel != null) {
                        session.beginTransaction();
                        session.remove(categoryDel);
                        session.getTransaction().commit();
                        System.out.println("Категорію видалено.");
                    } else {
                        System.out.println("Категорії з таким ID не існує.");
                    }
                    break;
                case "3":
                    PrintCategories(session);
                    System.out.println("Enter a id category, which you want to update:");
                    String selectedIdStr = scanner.nextLine();
                    int idCategory;
                    try {
                        idCategory = Integer.parseInt(selectedIdStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid format ID!");
                        return;
                    }

                    CategoryEntity categoryUpdate = session.find(CategoryEntity.class, idCategory);

                    if (categoryUpdate != null) {
                        System.out.println("Enter a new name for category: ");
                        var newName = scanner.nextLine();
                        session.beginTransaction();
                        categoryUpdate.setName(newName);
                        session.getTransaction().commit();
                        System.out.println("Category updated.");
                    } else {
                        System.out.println("Category with this ID not exists.");
                    }
                    break;
                case "4":
                    PrintCategories(session);
                    break;
                case "0":
                    session.close();
                    System.out.println("Exit...");
                    break;
            }
        }
    }
    private static List<CategoryEntity> GetListCategories(org.hibernate.Session session)
    {
        return session.createQuery("SELECT a FROM CategoryEntity a", CategoryEntity.class).getResultList();
    }
    private static void PrintCategories(org.hibernate.Session session)
    {
        var categories = GetListCategories(session);
        for (int i = 0; i < categories.size(); i++) {
            var currentCategory = categories.get(i);
            System.out.println("[" + currentCategory.getId() + "] " + currentCategory.getName());
        }
    }
}