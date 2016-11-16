package Homework8;
import static org.junit.Assert.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.*;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Testing {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	Dictionary dic;
	String [] words1 ={"noun:house","noun:cat","    noun     :     dog    ","adjective:small","verb:eat","verb : bike","verb:run",
			"adjective:purple","	adverb:quickly","         adverb:vehemently"};
	String [] words2 ={"noun:house","noun:cat","    noun     :     dog    ","adjective:small","verb:eat","verb : bike","verb:run",
			"adjective:purple","	adverb:quickly","         adverb:vehemently","unknown:word", "invalid:", "format"};
	String [] words3 ={"noun:house","adjective:small","verb:eat","	adverb:quickly"};
	Template template;

	@Before
	public void setUp()
	{
		dic = new Dictionary();
		System.setOut(new PrintStream(outContent));

	}

	@After
	public void tearDown()
	{ 
		this.dic =null;
		System.setOut(null);
	}

	private Object getField( Object instance, String name ) throws Exception
	{
		Class c = instance.getClass();
		Field f = c.getDeclaredField( name );
		f.setAccessible( true );
		return f.get( instance );
	}

	@Test
	public void A1_TestDictionary_addWord() throws Exception
	{
		for(String str:words1)
			dic.addWord(str);

		List <String> list = (List)getField(dic,"nouns");	
		assertEquals("house",list.get(0));
		assertEquals("cat",list.get(1));
		assertEquals("dog",list.get(2));

		list = (List)getField(dic,"verbs");
		assertEquals("eat",list.get(0));
		assertEquals("bike",list.get(1));
		assertEquals("run",list.get(2));

		list = (List)getField(dic,"adjectives");
		assertEquals("small",list.get(0));
		assertEquals("purple",list.get(1));

		list = (List)getField(dic,"adverbs");
		assertEquals("quickly",list.get(0));
		assertEquals("vehemently",list.get(1));

		list = (List)getField(dic,"pronouns");
		assertEquals(0,list.size());

		list = (List)getField(dic,"interjections");
		assertEquals(0,list.size());
	}

	@Test(expected=DictionaryFormatException.class)
	public void A2_TestDictionary_addWord() throws Exception{
		dic.addWord("format");
		assertEquals("[Error]: Invalid dictionary entry: 'format'\r\n", outContent.toString());	
	}

	@Test(expected=UnsupportedCategoryException.class)
	public void A3_TestDictionary_addWord() throws Exception{
		dic.addWord("unknown:word");
		assertEquals("[Error]: Unsupported category: 'unknown'\r\n", outContent.toString());	
	}



	@Test 
	public void A4_TestDictionary_getWord() throws Exception 
	{
		String[] fields = {"nouns", "verbs","adjectives","adverbs","pronouns","interjections"};
		int [] len = new int[fields.length];
		int [] newLen = new int[fields.length];

		List <String> list;

		int sum = 0;		
		for (int i = 0; i<fields.length;i++){
			list = (List)getField(dic,fields[i]);
			len[i] = list.size();
			sum += list.size();
		}
		assertEquals(0,sum);

		for(String str:words1)
			dic.addWord(str);

		sum = 0;		
		for (int i = 0; i<fields.length;i++){
			list = (List)getField(dic,fields[i]);
			len[i] = list.size();
			sum += list.size();
		}
		assertEquals(words1.length,sum);



		dic.getWord("adverb");
		dic.getWord("adjective");
		dic.getWord("verb");
		dic.getWord("noun");
		
		for (int i = 0; i<fields.length;i++){
			list = (List)getField(dic,fields[i]);
			newLen[i] = list.size();
		}
		for (int i=0;i<len.length;i++){
			if(i>3)
				assertEquals(len[i],newLen[i]);
			else
				assertEquals(len[i]-1,newLen[i]);
		}

		sum = 0;		
		for (String str: fields){
			list = (List)getField(dic,str);
			sum += list.size();
		}
		assertEquals(words1.length-4,sum);
	}

	@Test(expected=EmptyWordListException.class)
	public void A5_TestDictionary_getWord()
	{
		for(String str:words1)
			dic.addWord(str);
		dic.getWord("pronoun");
		assertEquals("[Error]: No remaining words of category: 'pronoun'\r\n", outContent.toString());	


	}

	@Test(expected=UnsupportedCategoryException.class)
	public void A6_TestDictionary_getWord() 
	{
		for(String str:words1)
			dic.addWord(str);
		dic.getWord("dummy");
		assertEquals("[Error]: Unsupported category: 'dummy'\r\n", outContent.toString());

	}

	@Test 
	public void B1_TestTemplate_Constructor() throws Exception
	{		
		template = new Template ("This is a test");
		assertEquals("This is a test",(String) getField(template,"template"));
	}

	@Test 
	public void B2_TestTemplate_fill() throws Exception
	{	//words3 ={"noun:house","adjective:small","verb:eat","	adverb:quickly"};
		for(String str:words3)
			dic.addWord(str);
		template = new Template ("The / noun / adverb / verb into the / adjective / noun .");
		assertEquals("The house quickly eat into the small / noun . ",template.fill(dic));

	}


	@Test 
	public void B3_TestTemplate_fill() throws Exception
	{	
		for(String str:words3)
			dic.addWord(str);
		template = new Template ("The / badpos / adjective / noun / verb a / adjective / noun on the / adjective / noun !");
		assertEquals("The / badpos small house eat a / adjective / noun on the / adjective / noun ! ",template.fill(dic));
		String expected = String.format("[Error]: Unsupported category: 'badpos'%n[Error]: No remaining words of category: 'adjective'%n"
				+ "[Error]: No remaining words of category: 'noun'%n[Error]: No remaining words of category: 'adjective'%n"
				+ "[Error]: No remaining words of category: 'noun'%n");
		assertEquals(expected, outContent.toString());	
	}
}
