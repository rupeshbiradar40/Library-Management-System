// Written by - Biradar rupesh Rajkumar
// UTD- Id - 2021463884
// NetId - rrb180004


package assignment4;

import java.io.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class LibraryManagementSystem extends JFrame 
{
//------------------------------------------Defined all the frame variables-------------------------//
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private String Book_Type;
	private String Title;
	private String ISBN ;
	private String DDSN;
	private String Author ;
	private String Publisher;
	private String Edition ;
	private String Department ;
	private String Location ;
	private String Total_Copies ;
	private String SearchTitle;
	private String SearchAuthor;
	private String SearchISBN;
	private String SearchDDSN;
	//---------------Defined an Array of Book Type and Publishers so that It can be selected from Dropdown ----------//
	private String[] BookTypeStrings = {"PaperBack", "Ebook"};
	private String[] PublisherStrings = {"Oxford University Press","HarperFlamingo","HarperPerennial","Farrar Straus And Giroux", "W. W. Norton &amp Company", "G. P. Putnam Sons", "Berkley Trade", "Simon &amp Schuster Audio", "Random House","Scribner","Emblem Editions"};
	private String[] AuthorNames = {"Mark P. O. Morford","Richard Bruce Wright","Carlo DEste","Gina Kolata","Elizabeth Wayland Barber","Amy Tan","Robert Cowley","Scott Turow","John David","Ann Beattie","David Adams Richards","Kensington","Sheila Heti","J. R. Kaiser","Mark Victor","Loren D. Estleman","Robert Hendrickson","Julia Oliver","John Grisham","Toni Morrison","Mike Loew","Philip Webb","J. R. Parrish","Ashley Olsen","Susan R. Sloan"};
	public Object table1[][] = new Object[50][10];
	static public Object column[] = { "Book_Type","ISBN", "DDSN", "Title",  "Author", "Publisher", "Edition","Department", "Location", "Total_Copies"};
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryManagementSystem frame = new LibraryManagementSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibraryManagementSystem() {
		
//-------------Initialized the JFrame Parameters like JTextLabel, JTextfields, JButtons and ScrollPane and JTable-----------//
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Library Management System");
		
		JLabel lblBooktype = new JLabel("Book_Type");
		lblBooktype.setBounds(49, 39, 86, 14);
		contentPane.add(lblBooktype);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(49, 64, 59, 14);
		contentPane.add(lblIsbn);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 61, 136, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDdsn = new JLabel("DDSN");
		lblDdsn.setBounds(49, 89, 59, 14);
		contentPane.add(lblDdsn);
		
		textField_2 = new JTextField();
		textField_2.setBounds(145, 86, 136, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(49, 114, 59, 14);
		contentPane.add(lblTitle);
		
		textField_3 = new JTextField();
		textField_3.setBounds(145, 111, 136, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox = new JComboBox(BookTypeStrings);
		comboBox.setRenderer(new MyComboBoxRenderer("Select Book Type"));
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(145, 36, 136, 20);
		contentPane.add(comboBox);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(49, 139, 59, 14);
		contentPane.add(lblAuthor);
		
		textField = new JTextField();
		textField.setBounds(145, 136, 136, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setBounds(380, 39, 76, 14);
		contentPane.add(lblPublisher);
		
		JComboBox comboBox_1 = new JComboBox(PublisherStrings);
		comboBox_1.setRenderer(new MyComboBoxRenderer("Select Publisher"));
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(490, 36, 136, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setBounds(380, 64, 70, 14);
		contentPane.add(lblEdition);
		
		textField_4 = new JTextField();
		textField_4.setBounds(490, 61, 136, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(380, 89, 76, 14);
		contentPane.add(lblDepartment);
		
		textField_5 = new JTextField();
		textField_5.setBounds(490, 86, 136, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(380, 114, 70, 14);
		contentPane.add(lblLocation);
		
		textField_6 = new JTextField();
		textField_6.setBounds(490, 111, 136, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblTotalcopies = new JLabel("Total_Copies");
		lblTotalcopies.setBounds(380, 139, 86, 14);
		contentPane.add(lblTotalcopies);
		
		textField_7 = new JTextField();
		textField_7.setBounds(490, 136, 136, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
//-------------------------Implemented logic in ActionPerformed method of JButton class to Add the data entered into the TextFields of Swing UI to DataBase--------------//
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//--------------------------Validated the NOT NULL, UNIQUE and Primary Key Constraints and Caught them into Exceptions-----------//
				try 
				{
				     Book_Type=comboBox.getSelectedItem().toString();
				     Title = textField_3.getText();
				     ISBN = textField_1.getText();
				     DDSN = textField_2.getText();
				     Author = textField.getText();
				     Publisher = comboBox_1.getSelectedItem().toString();
			         Edition = textField_4.getText();
				     Department = textField_5.getText();
				     Location = textField_6.getText();
				     Total_Copies=textField_7.getText();
				    
				     if (ISBN.length()!=10 || (!DDSN.isEmpty() && DDSN.length()!=10 ))
				     {
				    	 JOptionPane.showMessageDialog(null, "ISBN or DDSN Value Should not be more than 10 ");
				    	 return;
				     }
				}
				catch ( NullPointerException e2)
				{
					JOptionPane.showMessageDialog(null, "All the Fields should be Compulsorily filled except DDSN and Publisher");
				    return;
				}
//--------------------------------Implemented the Logic for separating Authors from TextFields and Made sure that--------------------//
				             
//--------------------------------The book is only added when all the Authors separated by ";" that are present in DB----------------//
				try 
				{
					int count=0; 
					String [] allAuthors = Author.split(";");
					for(int i=0;i<allAuthors.length;i++)
					{
						System.out.println(allAuthors[i]);
					}
					
					 for(int j=0;j<allAuthors.length;j++)
					  {
						 for (int i=0;i<AuthorNames.length;i++)
					     {
						    if(allAuthors[j].compareToIgnoreCase(AuthorNames[i])==0)
						    	count++;  
					     }   
					  }
					  System.out.println(count);
					if(count==allAuthors.length)  
					    {
//--------------------------------Created A connection and Wrote a stored Procedure and called it using Prepared Statements to add a Book to Book table ------------//
						  Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
							Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Welcome!10");
							Statement statement = connection.createStatement(); 
								 CallableStatement callstat=connection.prepareCall("{call AddaNewRecord(?,?,?,?,?,?,?,?,?,?)}");
								 callstat.setString(1,Book_Type);
					        	 callstat.setString(2,ISBN);
					        	 callstat.setString(3,DDSN);
					        	 callstat.setString(4,Title);
					        	 callstat.setString(5,Author);
					        	 callstat.setString(6,Publisher);
					        	 callstat.setString(7,Edition);
					        	 callstat.setString(8,Department);
					        	 callstat.setString(9,Location);
					        	 callstat.setString(10,Total_Copies); 
							     callstat.executeQuery();
							     PreparedStatement callstat1 = connection.prepareStatement("{call ShowResource(?)}");
							     callstat1.setString(1,ISBN);
							     ResultSet rs= callstat1.executeQuery();	
							     resultSetToTableModel(rs,table);
								 JOptionPane.showMessageDialog(null, "The Resource has been added Successfully");
								 statement.close();
								 connection.close();  
								 
					    }
//-------------------------If the value entered in the Author TextField doesn't match with the author present in the Database then added the pop-up message to Enter Existing Author Names only------------// 
							 else 
							 {			        	

								 JOptionPane.showMessageDialog(null, "Enter Existing Author Names only");		 
							 }
					
			      }
//----------------------Handled all other errors in the exception object e1 and displayed the corresponding error message-------------//			           	   
				catch(Exception e1)
				{
					
					JOptionPane.showMessageDialog(null, e1, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				    
				}
			}
			
			
		});
		
		btnAdd.setBounds(83, 189, 89, 23);
		contentPane.add(btnAdd);
//----------------Implemented the logic for Searching the data From DB when Title/ Author/ DDSN and ISBN is provided --------------------//
//----------------Used Like Keyword to search with particular piece of input of an attribute-----------------------//
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					SearchTitle = textField_3.getText();
					SearchAuthor = textField.getText();
					SearchISBN = textField_1.getText();
					SearchDDSN = textField_2.getText();
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Welcome!10");
					Statement statement = connection.createStatement(); 
//-----------------------------Search from DB when Only DDSN or ISBN or Both are given------------------------------------//					
					if(SearchAuthor.isEmpty()&&SearchTitle.isEmpty())
					{
						PreparedStatement prepstatement = connection.prepareStatement("SELECT * FROM Resources WHERE ISBN=? OR DDSN=?");
						prepstatement.setString(1,SearchISBN);
					    prepstatement.setString(2,SearchDDSN);
					    System.out.println(prepstatement.toString());
					    ResultSet rs =prepstatement.executeQuery();
					    resultSetToTableModel(rs,table);
					    
					}
//-----------------------------Search from DB when Author or DDSN or ISBN or Combination of them is given------------------------------------//						
					else if(!SearchAuthor.isEmpty()&&SearchTitle.isEmpty())
					{ 
						
						{
						PreparedStatement prepstatement = connection.prepareStatement("SELECT * FROM Resources WHERE Author LIKE ? OR ISBN=? OR DDSN=?");
						prepstatement.setString(1,'%'+ SearchAuthor + '%');
						prepstatement.setString(2,SearchISBN);
					    prepstatement.setString(3,SearchDDSN);
					    System.out.println(prepstatement.toString());
					    ResultSet rs =prepstatement.executeQuery();
					    if (!rs.isBeforeFirst() ) {    
					    	JOptionPane.showMessageDialog(null, "Author with given name doesn't exixt");
					    } 
					    else
					    resultSetToTableModel(rs,table);
						}
					}
//-----------------------------Search from DB when Title or DDSN or ISBN or Combination of them is given------------------------------------//				
					else if(SearchAuthor.isEmpty()&&(!SearchTitle.isEmpty()))
					{
						PreparedStatement prepstatement = connection.prepareStatement("SELECT * FROM Resources WHERE Title Like ? OR ISBN=? OR DDSN=?");
						
						prepstatement.setString(1,'%'+ SearchTitle + '%');
					    prepstatement.setString(2,SearchISBN);
					    prepstatement.setString(3,SearchDDSN);
					    System.out.println(prepstatement.toString());
					    ResultSet rs =prepstatement.executeQuery();
					   
					    if (!rs.isBeforeFirst() )
					    {    
					    	JOptionPane.showMessageDialog(null, "Book with given Title doesn't exist");
					    } 
					    else
					    resultSetToTableModel(rs,table);
					}
					    
//-------------------------------------Covered all other combinational cases of searching with provided input cases-----------------------------------------------------------------//					
					else
					{
				     PreparedStatement prepstatement = connection.prepareStatement("SELECT * FROM Resources WHERE Author LIKE ? OR Title Like ? OR ISBN=? OR DDSN=?");
				     System.out.println(prepstatement);
				     prepstatement.setString(1,'%'+ SearchAuthor + '%');
				     prepstatement.setString(2,'%'+ SearchTitle + '%');
				     prepstatement.setString(3,SearchISBN);
				     prepstatement.setString(4,SearchDDSN);
				     System.out.println(prepstatement.toString());
				     ResultSet rs =prepstatement.executeQuery();
				     if (!rs.isBeforeFirst() )
					    {    
				    	 JOptionPane.showMessageDialog(null, "Author or book with given title doesn't exist");
					    } 
					 else
				         resultSetToTableModel(rs,table);
					}     
			             statement.close();
					     connection.close();  
				      
				}
				catch(Exception e1)
				{
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, e1, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
					dispose();
				}	
			}
		});
		btnSearch.setBounds(220, 189, 89, 23);
		contentPane.add(btnSearch);
//----------------------------Implemented the logic for the updating details of a book table. Updated Values like Edition, Location and Total_Copies using ISBN---//		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try 
				{
				     ISBN = textField_1.getText();
			         Edition = textField_4.getText();
				     Location = textField_6.getText();
				     Total_Copies=textField_7.getText();
//--------------------wrote a queries to update provided combination of resources---------------------------------------------------//
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Welcome!10");
					Statement statement = connection.createStatement(); 
					if(!Location.isEmpty()&& !Edition.isEmpty()&&!Total_Copies.isEmpty())
					{
						statement.executeUpdate("UPDATE Resources SET Edition='"+Edition+"', Location='"+Location+"'Total_Copies='"+Total_Copies+"' WHERE ISBN='"+ISBN+"';");
						ResultSet rs= statement.executeQuery("SELECT * FROM RESOURCES WHERE ISBN='"+ISBN+"';");	
						resultSetToTableModel(rs,table);
					}
					else if(Location.isEmpty()&&!Edition.isEmpty()&&!Total_Copies.isEmpty())
					{
						statement.executeUpdate("UPDATE Resources SET  Total_Copies='"+Total_Copies+"' WHERE ISBN='"+ISBN+"';");
						ResultSet rs= statement.executeQuery("SELECT * FROM RESOURCES WHERE ISBN='"+ISBN+"';");	
						resultSetToTableModel(rs,table);
					}
					else if(Location.isEmpty()&&!Edition.isEmpty()&&Total_Copies.isEmpty())
					{
						statement.executeUpdate("UPDATE Resources SET  Edition='"+Edition+"' WHERE ISBN='"+ISBN+"';");
						ResultSet rs= statement.executeQuery("SELECT * FROM RESOURCES WHERE ISBN='"+ISBN+"';");	
						resultSetToTableModel(rs,table);
					}
					else if(!Location.isEmpty()&&Edition.isEmpty()&&!Total_Copies.isEmpty())
					{
						statement.executeUpdate("UPDATE Resources SET  Location='"+Location+"',Totl_Copies='"+Total_Copies+"' WHERE ISBN='"+ISBN+"';");
						ResultSet rs= statement.executeQuery("SELECT * FROM RESOURCES WHERE ISBN='"+ISBN+"';");	
						resultSetToTableModel(rs,table);
					}
					else if(!Location.isEmpty()&&Edition.isEmpty()&&Total_Copies.isEmpty())
					{
						statement.executeUpdate("UPDATE Resources SET  Location='"+Location+"' WHERE ISBN='"+ISBN+"';");
						ResultSet rs= statement.executeQuery("SELECT * FROM RESOURCES WHERE ISBN='"+ISBN+"';");	
						resultSetToTableModel(rs,table);
					}
					else if(!Location.isEmpty()&&!Edition.isEmpty()&&Total_Copies.isEmpty())
					{
						statement.executeUpdate("UPDATE Resources SET  Location='"+Location+"', Edition='"+Edition+"' WHERE ISBN='"+ISBN+"';");
						ResultSet rs= statement.executeQuery("SELECT * FROM RESOURCES WHERE ISBN='"+ISBN+"';");	
						resultSetToTableModel(rs,table);
					}
					else if(!Location.isEmpty()&&!Edition.isEmpty()&&Total_Copies.isEmpty())
					{
						statement.executeUpdate("UPDATE Resources SET  Location='"+Location+"', Edition='"+Edition+"' WHERE ISBN='"+ISBN+"';");
						ResultSet rs= statement.executeQuery("SELECT * FROM RESOURCES WHERE ISBN='"+ISBN+"';");	
						resultSetToTableModel(rs,table);
					}
					else if (Location.isEmpty()&&Edition.isEmpty()&&Total_Copies.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Enter Values of Location, Total_Copies and Edtion of a resource to update along with its ISBN" );
					}
			        statement.close();
					connection.close();  
					
				}
				catch(Exception e1)
				{
					//System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, e1, "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
					dispose();
				}	
			}
		});
		btnUpdate.setBounds(361, 189, 89, 23);
		contentPane.add(btnUpdate);
//---------------Set null to all textFileds when a clear button is pressed --------------------//		
		JButton btnDelete = new JButton("CLEAR");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 textField.setText("");
			     textField_1.setText("");
			     textField_2.setText("");
			     textField_3.setText("");
		         textField_4.setText("");
			     textField_5.setText("");
			     textField_6.setText("");
			     textField_7.setText("");
			     comboBox.setSelectedIndex(-1);
			     comboBox_1.setSelectedIndex(-1);
			     ((DefaultTableModel)table.getModel()).setNumRows(0);
			}
		});
		

		btnDelete.setBounds(511, 189, 89, 23);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 234, 577, 127);
		contentPane.add(scrollPane);
		
		table = new JTable(table1,column);
		scrollPane.setViewportView(table);
	}
//----------------------Implemented resultSetToTableModel method to display the ResultSet of a query into the JJable of Swing UI--------------------//
	
	 public void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException
	 {
    
         DefaultTableModel tableModel = new DefaultTableModel();
         ResultSetMetaData metaData = rs.getMetaData();
         int columnCount = metaData.getColumnCount();
         for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
         {
             tableModel.addColumn(metaData.getColumnLabel(columnIndex));
         }

         Object[] row = new Object[columnCount];

         while (rs.next())
         { 
             for (int i = 0; i < columnCount; i++)
             {
                 row[i] = rs.getObject(i+1);
             } 
             tableModel.addRow(row);
         }
         table.setModel(tableModel);
     }
	
}

//---------------This Class is Implemented to Give title to the DropDown box of  Book_Type and Publisher--------------//
class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
	  private String _title;

	  public MyComboBoxRenderer(String title) {
	    _title = title;
	  }

	  @Override
	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean hasFocus) {
	    if (index == -1 && value == null)
	      setText(_title);
	    else
	      setText(value.toString());
	    return this;
	  }
}


