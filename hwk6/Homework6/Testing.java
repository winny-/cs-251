// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework5
// Date:       22-Oct-2016
//
// -- Comments --
//
// I changed the package name to Homework6.
//
// All unit tests pass.

package Homework6;


import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Testing {

    Window window;
	
    WindowOrder windoworder;
	
    Room room;
    Room masterbedroom;
    Room guestroom;
    Room livingroom;
	
    Apartment apartment;
    Apartment onebedroom;
    Apartment twobedrom;
    Apartment threebedroom;
	
    Building building;
    Room []rooms ;
    Apartment [] apartments;

    @Before
    public void setUp()
    {
    	this.window= new Window(10, 20);
    	windoworder = new WindowOrder(window, 100);
    	this.room= new Room(window, 5);
    	this.masterbedroom= new MasterBedroom();
    	this.guestroom= new GuestRoom();
    	this.livingroom= new LivingRoom();
    	
    	rooms =new Room[5];
    	rooms[0]=masterbedroom;
    	rooms[1]=guestroom;
    	rooms[2]=livingroom;
    	rooms[3]=masterbedroom;
    	rooms[4]=livingroom;
    	
    	this.apartment= new Apartment(30, rooms);
    	this.onebedroom= new OneBedroom(10);
    	this.twobedrom=new TwoBedroom(5);
    	this.threebedroom=new ThreeBedroom(15);
    	
    	apartments = new Apartment[6];
    	apartments[0] = onebedroom;
    	apartments[1] = twobedrom;
    	apartments[2] = threebedroom;
    	apartments[3] = onebedroom;
    	apartments[4] = onebedroom;
    	apartments[5] = twobedrom;

    	this.building= new Building(apartments);
    }

    @After
    public void tearDown()
    {    	
    	this.window= null;
    	this.windoworder=null;
    	this.room= null;
    	this.masterbedroom= null;
    	this.guestroom= null;
    	this.livingroom= null;
    	this.apartment= null;
    	this.onebedroom= null;
    	this.twobedrom=null;
    	this.threebedroom=null;
    	this.building=null;
    }
    
    private Object getField( Object instance, String name ) throws Exception
    {
        Class c = instance.getClass();
        Field f = c.getDeclaredField( name );
        f.setAccessible( true );
        return f.get( instance );
    }

    @Test 
    public void AA_TestWindowConstructor() throws Exception{
    	assertEquals(this.window.getClass().getSimpleName().toString(), "Window");
        assertEquals(10,(int)getField(window,"width"));
        assertEquals(20,getField(window,"height"));
    }
    @Test 
    public void AB_TestWindowEquals() throws Exception{
    	assertTrue(this.window.equals(new Window(10,20)));
    	assertTrue(this.window.equals(this.window));
    	assertFalse(this.window.equals(new Window(1,20)));
		
    }
    @Test 
    public void BA_TestWindowOrderConstructor(){
    	assertEquals(this.windoworder.getClass().getSimpleName().toString(), "WindowOrder");
        assertEquals(this.window,windoworder.window);
        assertEquals(100,windoworder.num);
    }
    @Test 
    public void BB_Testwindoworderadd(){
    	WindowOrder w=windoworder.add(new WindowOrder(new Window(10,20), 1000));
    	assertEquals(windoworder,w);
        assertEquals(1100,windoworder.num);
        windoworder=null;
    }
    @Test 
    public void BC_Testwindoworderadd(){
    	WindowOrder w= windoworder.add(new WindowOrder(new Window(20,20), 1000));
    	assertEquals(windoworder,w);
        assertEquals(100,windoworder.num);
    }
    @Test 
    public void BD_Testwindoworderadd(){
    	WindowOrder w= windoworder.add(windoworder);
    	assertEquals(windoworder,w);
        assertEquals(200,windoworder.num);
    }
    @Test 
    public void BE_Testwindowordertimes(){
    	WindowOrder w= windoworder.times(0);
    	assertEquals(windoworder,w);
        assertEquals(0,windoworder.num);
    }
    @Test 
    public void BF_Testwindowordertimes(){
    	WindowOrder w= windoworder.times(3);
    	assertEquals(windoworder,w);
        assertEquals(300,windoworder.num);
    }
    @Test 
    public void CA_TestRoomConstructor(){
    	assertEquals(this.room.getClass().getSimpleName().toString(), "Room");
        assertEquals(5,room.numOfWindows);
        assertEquals(window,room.window);
    }
    @Test 
    public void CB_TestRoomOrder(){
    	
        assertEquals( new WindowOrder(window, 5),room.order());
        assertEquals( new WindowOrder(new Window(4, 6), 3),masterbedroom.order());
    }
    @Test 
    public void D_TestMasterBedRoomConstructor(){
    	assertEquals(this.masterbedroom.getClass().getSimpleName().toString(), "MasterBedroom");
        assertEquals(3,masterbedroom.numOfWindows);
        assertEquals(new Window(4, 6),masterbedroom.window);
    }
    @Test 
    public void E_TestGuestRoomConstructor(){
    	assertEquals(this.guestroom.getClass().getSimpleName().toString(), "GuestRoom");
        assertEquals(2,guestroom.numOfWindows);
        assertEquals(new Window(5, 6),guestroom.window);
    }
    @Test 
    public void F_TestLivingRoomConstructor(){
    	assertEquals(this.livingroom.getClass().getSimpleName().toString(), "LivingRoom");
        assertEquals(5,livingroom.numOfWindows);
        assertEquals(new Window(6, 8),livingroom.window);
    }
    @Test 
    public void GA_TestApartmentConstructor(){
    	assertEquals(this.apartment.getClass().getSimpleName().toString(), "Apartment");
        assertEquals(30,apartment.numOfUnits);
        assertArrayEquals(rooms,apartment.rooms);
    }
    @Test 
    public void GB_TestApartmentTotalOrder(){
    	WindowOrder[] wo = new WindowOrder [5];
    	wo[0] = new WindowOrder(new Window(4, 6), 90);
    	wo[1] = new WindowOrder(new Window(5, 6), 60);
    	wo[2] = new WindowOrder(new Window(6, 8), 150);
    	wo[3] = new WindowOrder(new Window(4, 6), 90);
    	wo[4] = new WindowOrder(new Window(6, 8), 150);
    	
    	assertArrayEquals(wo ,apartment.totalOrder());
		
    }
    @Test 
    public void HA_TestOneBedroomConstructor(){
    	assertEquals(this.onebedroom.getClass().getSimpleName().toString(), "OneBedroom");
    	assertEquals(10,onebedroom.numOfUnits);
        assertArrayEquals(new Room[] { new LivingRoom(), new MasterBedroom() },onebedroom.rooms);
    }
    @Test 
    public void HB_TestOneBedroomorDerForOneUnit(){
    	WindowOrder[] wo = new WindowOrder [2];
    	wo[0] = new WindowOrder(new Window(6, 8), 5);
    	wo[1] = new WindowOrder(new Window(4, 6), 3);
  
    	assertArrayEquals(wo ,onebedroom.orderForOneUnit());
    }
    @Test 
    public void HC_TestOneBedroomTotalOrder(){
    	WindowOrder[] wo = new WindowOrder [2];
    	wo[0] = new WindowOrder(new Window(6, 8), 50);
    	wo[1] = new WindowOrder(new Window(4, 6), 30);
  
    	assertArrayEquals(wo ,onebedroom.totalOrder());
    }
 
    @Test 
    public void IA_TestTwoBedroomConstructor(){
    	assertEquals(this.twobedrom.getClass().getSimpleName().toString(), "TwoBedroom");
    	assertEquals(5,twobedrom.numOfUnits);
        assertArrayEquals(new Room[] { new LivingRoom(), new MasterBedroom(), new GuestRoom() },twobedrom.rooms);
    }
    @Test 
    public void IB_TestTwoBedroomOrderForOneUnit(){
    	WindowOrder[] wo = new WindowOrder [3];
    	wo[0] = new WindowOrder(new Window(6, 8), 5);
    	wo[1] = new WindowOrder(new Window(4, 6), 3);
    	wo[2] = new WindowOrder(new Window(5, 6), 2);
  
    	assertArrayEquals(wo ,twobedrom.orderForOneUnit());
    }
    @Test 
    public void IC_TestTwoBedroomTotalOrder(){
    	WindowOrder[] wo = new WindowOrder [3];
    	wo[0] = new WindowOrder(new Window(6, 8), 25);
    	wo[1] = new WindowOrder(new Window(4, 6), 15);
    	wo[2] = new WindowOrder(new Window(5, 6), 10);
  
    	assertArrayEquals(wo ,twobedrom.totalOrder());
    }
   
    @Test 
    public void JA_TestThreeBedroomConstructor(){
    	assertEquals(this.threebedroom.getClass().getSimpleName().toString(), "ThreeBedroom");
    	assertEquals(15,threebedroom.numOfUnits);
        assertArrayEquals(new Room[] { new LivingRoom(), new MasterBedroom(), new GuestRoom(), new GuestRoom() },threebedroom.rooms);
    }
    @Test 
    public void JB_TestThreeBedroomOrderForOneUnit(){
    	WindowOrder[] wo = new WindowOrder [3];
    	wo[0] = new WindowOrder(new Window(6, 8), 5);
    	wo[1] = new WindowOrder(new Window(4, 6), 3);
    	wo[2] = new WindowOrder(new Window(5, 6), 4);
  
    	assertArrayEquals(wo ,threebedroom.orderForOneUnit());
    }
    @Test 
    public void JC_TestThreeBedroomTotalOrder(){
    	WindowOrder[] wo = new WindowOrder [3];
    	wo[0] = new WindowOrder(new Window(6, 8), 75);
    	wo[1] = new WindowOrder(new Window(4, 6), 45);
    	wo[2] = new WindowOrder(new Window(5, 6), 60);
  
    	assertArrayEquals(wo ,threebedroom.totalOrder());
    }
   
    @Test 
    public void KA_TestBuildingConstructor(){
    	assertEquals(this.building.getClass().getSimpleName().toString(), "Building");
        assertArrayEquals(apartments,building.apartments);
    }
    @Test 
    public void KB_TestBuildingOrderr(){
    	WindowOrder[] wo = new WindowOrder [3];
    	wo[0] = new WindowOrder(new Window(6, 8), 275);
    	wo[1] = new WindowOrder(new Window(4, 6), 165);
    	wo[2] = new WindowOrder(new Window(5, 6), 80);
    	
        assertArrayEquals(wo,building.order());
    }
    

    @Test 
    public void L_TestWindowToString(){
    	String expected= "10 X 20 window";
    	
        assertEquals(expected,window.toString());
    }
    @Test 
    public void M_TestWindowOrderToString(){
    	String expected= "100 10 X 20 window";
    	
        assertEquals(expected,windoworder.toString());
    }
    @Test 
    public void N_TestApartmentToString(){
    	String expected= "30 apartments with (Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))(Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Living room: 5 (6 X 8 window))";
    	
        assertEquals(expected,apartment.toString());
    }
    
    @Test 
    public void O_TestoneBedRoomToString(){
    	String expected= "10 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))";
    	
        assertEquals(expected,onebedroom.toString());
    }
    
    @Test 
    public void P_TestTwoBedRoomToString(){
    	String expected= "5 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))";
    	
        assertEquals(expected,twobedrom.toString());
    }
    
    @Test 
    public void Q_TestThreeBedRoomToString(){
    	String expected= "15 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))(Guest room: 2 (5 X 6 window))";
    	
        assertEquals(expected,threebedroom.toString());
    }
    
    @Test 
    public void R_TestMasterRoomToString(){
    	String expected= "Master bedroom: 3 (4 X 6 window)";
    	
        assertEquals(expected,masterbedroom.toString());
    }
    @Test 
    public void S_TestGuestRoomToString(){
    	String expected= "Guest room: 2 (5 X 6 window)";
    	
        assertEquals(expected,guestroom.toString());
    }
    @Test 
    public void T_TestLivingRoomToString(){
    	String expected= "Living room: 5 (6 X 8 window)";
    	
        assertEquals(expected,livingroom.toString());
    }
    
    @Test 
    public void UTestBuildingToString(){
    	String expected= "10 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))\n"+
            "5 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))\n"+
            "15 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))(Guest room: 2 (5 X 6 window))\n"+
            "10 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))\n"+
            "10 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))\n"+
            "5 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))\n";
    	
        assertEquals(expected,building.toString());
    }
  
}


