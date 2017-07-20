package com.example.cookzyme.cookzyme.database;

import java.util.ArrayList;

public class Database {
	private ArrayList<Foods> arrayFood;
	private ArrayList<Ingredients> arrayIngredients;
	private ArrayList<HowToCook> arrayHowToCook;
	private ArrayList<HasIngredients> arrayHasIngredients;
	public Database() {
		// TODO Auto-generated constructor stub
		arrayFood=new ArrayList<Foods>();
		arrayIngredients = new ArrayList<Ingredients>();
		arrayHowToCook = new ArrayList<HowToCook>();
		arrayHasIngredients = new ArrayList<HasIngredients>();
		generateDB();
	}

	public ArrayList<Foods> getArrayFood() {
		return arrayFood;
	}

	public void setArrayFood(ArrayList<Foods> arrayFood) {
		this.arrayFood = arrayFood;
	}

	public ArrayList<Ingredients> getArrayIngredients() {
		return arrayIngredients;
	}

	public void setArrayIngredients(ArrayList<Ingredients> arrayIngredients) {
		this.arrayIngredients = arrayIngredients;
	}

	public ArrayList<HowToCook> getArrayHowToCook() {
		return arrayHowToCook;
	}

	public void setArrayHowToCook(ArrayList<HowToCook> arrayHowToCook) {
		this.arrayHowToCook = arrayHowToCook;
	}

	public ArrayList<HasIngredients> getArrayHasIngredients() {
		return arrayHasIngredients;
	}

	public void setArrayHasIngredients(ArrayList<HasIngredients> arrayHasIngredients) {
		this.arrayHasIngredients = arrayHasIngredients;
	}

	public void generateDB() {
		// TODO Auto-generated method stub
		arrayFood.add(new Foods("Pork Ball", "http://www.cutestpaw.com/wp-content/uploads/2014/08/corgi.jpg", 0,615));
		
		arrayIngredients.add(new Ingredients("Brown Onion", ""));
		arrayIngredients.add(new Ingredients("Cabbage", ""));
		arrayIngredients.add(new Ingredients("Chicken", ""));
		arrayIngredients.add(new Ingredients("Chicken Breast", ""));
		arrayIngredients.add(new Ingredients("Chili", ""));
		arrayIngredients.add(new Ingredients("Corainder", ""));
		arrayIngredients.add(new Ingredients("Cucumber", ""));
		arrayIngredients.add(new Ingredients("Egg", ""));
		arrayIngredients.add(new Ingredients("Enokitake", ""));
		arrayIngredients.add(new Ingredients("Garlic", ""));
		arrayIngredients.add(new Ingredients("Ginger", ""));
		arrayIngredients.add(new Ingredients("Lettuce", ""));
		arrayIngredients.add(new Ingredients("Minced Pork", ""));
		arrayIngredients.add(new Ingredients("Morning Glory", ""));
		arrayIngredients.add(new Ingredients("Oil", ""));
		arrayIngredients.add(new Ingredients("Oyster Sauce", ""));
		arrayIngredients.add(new Ingredients("Pork", ""));
		arrayIngredients.add(new Ingredients("Pumpkin", ""));
		arrayIngredients.add(new Ingredients("Rice", ""));
		arrayIngredients.add(new Ingredients("Sesame Oil", ""));
		arrayIngredients.add(new Ingredients("Shallots", ""));
		arrayIngredients.add(new Ingredients("Shrimp", ""));
		arrayIngredients.add(new Ingredients("Soy Sauce", ""));
		arrayIngredients.add(new Ingredients("Spring Onion", ""));
		arrayIngredients.add(new Ingredients("Squid", ""));
		arrayIngredients.add(new Ingredients("Suki Sauce", ""));
		arrayIngredients.add(new Ingredients("Tomato", ""));
		arrayIngredients.add(new Ingredients("Vermicelli", ""));
		arrayIngredients.add(new Ingredients("Wide Rice Noodle", ""));
		
		
		arrayHowToCook.add(new HowToCook("Pork Ball",1 , "ล้างสันในไก่ให้สะอาด สับให้ละเอียด สับหอมหัวใหญ่ ผักชี พักไว้ก่อนค่ะ"));
		arrayHowToCook.add(new HowToCook("Pork Ball",2 , "ตอกไข่ใส่ชาม ใส่น้ำ ตีให้เข้ากัน ค่อยๆเติมเเป้งข้าวโพด และคนให้เข้ากัน ตามด้วยน้ำมันงา ซีอิ๊วขาว พอเข้ากันดี เติมไก่สับลงไปค่ะ คลุกเคล้า ผักชี และหอมใหญ่สับ เกล็ดขนมปังลงไป เอามือคลุกให้เข้ากันค่ะ"));
		arrayHowToCook.add(new HowToCook("Pork Ball", 3, "พอเข้ากันดีเเล้ว ปั้นเป็นก้อนกลม"));
		arrayHowToCook.add(new HowToCook("Pork Ball", 4, "ปั้นเสร็จเเล้ว เอาไปแช่เย็นให้ ลูกบอล เหล่านี้ มันเซตตัวกลมๆค่ะ 20 นาที ก็พอค่ะ"));
		arrayHowToCook.add(new HowToCook("Pork Ball", 5, "ตั้งกระทะใส่น้ำมันสำหรับทอด กะให้ท่วม ลูกบอลนะค่ะ สูตรนี้ได้ 20 ลูก ทอด2 ครั้งได้ค่ะ การทอดที่สำคัญ น้ำมันควรจะร้อน ใช้ไฟกลาง ห้ามใช้ไฟเเรงนะค่ะเดี๋ยวข้างนอกไหม้ แล้วข้างในไม่สุก  เเต่ถ้าไฟอ่อนมากไป ใช้เวลาทอดนาน ลูกบอลอมน้ำมันเเน่นอนค่ะ เอากลางๆนะค่ะ เป็นหัวใจของการทอดเลยทีเดียว"));
		arrayHowToCook.add(new HowToCook("Pork Ball", 6, "ทอดพอได้สีเหลืองทอง ลองเอามาพิสูจน์สักลูกนะค่ะ ว่าสุกถึงเนื้อในหรือป่าว เลือกเอาลูกที่ใหญ่ที่สุด เพราะถ้าลูกใหญ่สุดสุกเเล้ว ลูกอื่นๆต้องสุกเเน่นอนค่ะ"));
		arrayHowToCook.add(new HowToCook("Pork Ball", 7, "พอสุกเเล้วจัดใส่จาน เสิร์ฟ กับน้ำจิ้มไก่ หรือ ซอสพริก ซอสมะเขือเทศ ตามชอบค่ะ"));
		arrayHowToCook.add(new HowToCook("ก๋วยเตี๋ยวราดหน้าหมูสับ",1 , "หมักหมูสับกับซีอิ้วขาวพักไว้ประมาณ 15 นาที"));
		arrayHowToCook.add(new HowToCook("ก๋วยเตี๋ยวราดหน้าหมูสับ",2 , "ขยี้เส้นก๋วยเตี๋ยวให้กระจาย ผัดในน้ำมันพอกรอบแล้วพักไว้"));
		arrayHowToCook.add(new HowToCook("ก๋วยเตี๋ยวราดหน้าหมูสับ",3 , "เจียวกระเทียมกับน้ำมันให้พอหอมแล้วใส่เนื้อหมูที่หมักไว้ ให้ตะหลิวขยิ้ให้กระจาย ผัดให้สุกแล้วใส่น้ำซุปลงไป ใส่หอมใหญ่ มะเขือเทศ น้ำตาลทราย จนเข้ากัน แล้วใส่แป้งมันที่ผสมกับน้ำเล็กน้อย ผัดกันให้เข้าเส้น อย่าผัดนานเพราะผักจะเหี่ยว"));
		arrayHowToCook.add(new HowToCook("ก๋วยเตี๋ยวราดหน้าหมูสับ",4 , "จัดจานด้วยการรองด้วยผักกาดหอม"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",1 , "ใส่น้ำเปล่า 1.5 ลิตร ลงในหม้อขนาดปานกลาง ต้มน้ำให้เดือด"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",2 , "วิธีทำน้ำซุป ใส่รากผักชี พริกไทย ลงไปต้มให้เดือด ให้ใช้ไฟอ่อนในการต้มเนื่องจากฟองจะได้ไม่เยอะ หากมีฟองให้ช้อนออกด้วยค่ะ"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",3 , "หมักหมู โดยใช้ส่วนผสม( กระทียม รากผักชี พริกไทย)นำทั้ง 3นี้มาโคลกให้เข้ากันจนละเอียด แล้วนำมาหมักกับหมูบด เหยาะน้ำปลาไป 2 ชช.คลุกเคล้าให้เข้ากัน นำไปแช่ตู้เย็น 15 นาที"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",4 , "ใส่ข้าวหอมมะลิ 2 ถ้วย ลงไปต้ม อย่าลืมนะค่ะควรใช้ไฟอ่อนในการต้มเสมอ ประมาณ 15 นาที คอยคนเป็นระยะๆ"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",5 , "เมื่อข้าวสุกได้ที่แตกตัวเป็นเม็ดสวยงามแล้ว ใส่กระเทียม 10 กลีบ นำหมูบดที่หมักไว้มาปั้นเป็นก้อนเล็กๆพอคำ ลงไปต้มรอจน หมูสุก และกระเทียมสุก น้ำซุปจะหวาน ไม่มีรสชาดของกระเทียมอยู่เลย วิธีเช็คง่ายๆ ว่ากระเทียมได้ที่หรือยัง ให้ใช้ไม้จิ้มดูว่านิ่มหรือยัง หากนิ่มแล้ว เป็นอันใช้ได้"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",6 , "สุดท้าย ปรุงรส ด้วยน้ำปลาอย่างดี 3 ช้อนโต๊ะ( สัดส่วนนี้อาจเปลี่ยนแปลงได้ตามผู้ที่ชอบ) ปิดเตาแก๊ส"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",7 , "ผักชี ต้นหอม นำมาหั่นซอยให้ละเอียด เตรียมไว้โรยหน้า"));
		arrayHowToCook.add(new HowToCook("ข้าวต้มหมูทรงเครื่อง",8 , "ตักใส่ถ้วย โรยผักตามชอบ โรยพริกไทยป่นเพิ่ม อร่อยค่ะ"));
		arrayHowToCook.add(new HowToCook("ข้าวผัดกุ้ง",1 , "นำกุ้งมาปลอกเปลือกแล้วล้างด้วยน้ำเกลือให้สะอาด เสร็จแล้วนำไปผึ่งให้สะเด็ดน้ำ นำเข้าช่องแช่เย็นไว้ก่อน"));
		arrayHowToCook.add(new HowToCook("ข้าวผัดกุ้ง",2 , "ตอกไข่ 3 ฟองลงในชาม และหั่นต้นหอม ผักชี เตรียมรอพักไว้"));
		arrayHowToCook.add(new HowToCook("ข้าวผัดกุ้ง",3 , "ใส่น้ำมันลงกะทะรอให้ร้อนจัด ๆ นำกุ้งที่แช่เย็นไว้ลงมาใส่ผัดให้สุก และเกลี่ยพักไว้ด้านข้างบนขอบกะทะ"));
		arrayHowToCook.add(new HowToCook("ข้าวผัดกุ้ง",4 , "เทไข่ที่อยู่ในชามลงไปตีให้พอสุก ตลบกุ้งที่พักไว้ด้านบนขอบกะทะผัดผสมรวมกับไข่ เพื่อให้ไข่เคลือบตัวกุ้งจะได้มีความหอมของไข่ในเนื้อกุ้งด้วย"));
		arrayHowToCook.add(new HowToCook("ข้าวผัดกุ้ง",5 , "ใส่ข้าวลงในกระทะ ตามด้วยซอสปรุงรส และน้ำตาล จากนั้นผัดให้ส่วนผสมทั้งหมดเข้ากันพอ แต่ไม่ควรที่จะผัดนานจนเกินไปเพราะทำให้ข้าวสุกมากเกินไปจะเละได้"));
		arrayHowToCook.add(new HowToCook("ข้าวผัดกุ้ง",6 , "พอส่วนผสมทั้งหมดเข้ากันดีแล้วให้เบาไฟลงหรือปิดไฟก็ได้ แล้วใส่ต้นหอม ผักชี พริกไทย ลงไปคนให้เข้ากันอีกครั้งยกขึ้นจากเตาเตรียมจัดพร้อมเสริฟ"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",1 , "เริ่มต้นด้วยการต้มน้ำซุปก่อนเลย ใส่ไก่ลงไป ใส่กระเทียม  พริกไทย รากผักชี ใส่น้ำลงไป ต้มให้เดือด"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",2 , "เดือดแล้วเปิดไฟอ่อนๆใส่เกลือ น้ำตาล ต้มไปเรื่อยๆซัก 25 - 30  นาทีก้อโอเคยกลงได้ อย่าลืมคอยช้อนฟอง"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",3 , "จากนั้นหันมาเตรียม ขิง กระเทียม พริก  เต้าเจี้ยวใส่เครื่องปั่น"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",4 , "แล้วก็ปั่นให้ค่อนข้างละเอียดนิดนึง ใครจะตำก็ได้"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",5 , "แล้วก็ใส่ซอสถั่วเหลือง น้ำตาลทราย น้ำส้มสายชู แล้วคนให้น้ำตาลละลายและเข้ากันดี"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",6 , "จากนั้นก็มาเริ่มผัดข้าว ตั้งกะทะใส่น้ำมันประมาณ 2 ใน 3 ถ้วย พอเริ่มร้อนใส่กระเทียมลงไปผัด"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",7 , "พอเริ่มหอมใส่ขิงลงไปผัด"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",8 , "พอหอมกลิ่นขิงก็ใส่เข้าสารลงไปผัด"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",9 , "ผัดให้น้ำมันเคลือบเม็ดข้าว จนข้าวเริ่มหอมและอมน้ำมันไว้ในเม็ดนิดๆ จะทำให้ข้าวมีรสชาติดีและสัมผัสดีกว่าเอาข้าวสารไปหุงกับน้ำเฉยๆ แถมเม็ดข้าวเมื่อหุงสุกยังเรียวสวย ไม่แตกไม่บานอีกด้วย"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",10 , "จากนั้นใส่น้ำซุปลงไป  ข้าวของเรา 2 ถ้วย ใส่น้ำลงไปประมาณ 3 ถ้วย กับอีก 1ใน3 ถ้วย"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",11 , "ค่อยๆคนให้ข้าวกับน้ำซุปเข้ากันแล้วเทลงหม้อหุงข้าว หรือใครจะปิดฝาแล้วหุงในกะทะเลยก็ได้"));
		arrayHowToCook.add(new HowToCook("ข้าวมันไก่",12 , "เมื่อข้าวสุกแล้ว หั่นไก่ลงไปโปะข้าว แล้วโปะด้วยผัชี และแตงกวา"));
		arrayHowToCook.add(new HowToCook("ข้าวไก่กระเทียม",1 , "ตำกระเทียมให้ละเอียด"));
		arrayHowToCook.add(new HowToCook("ข้าวไก่กระเทียม",2 , "นำไก่ลงผัดในน้ำมันร้อนๆ จนไก่สุกดี"));
		arrayHowToCook.add(new HowToCook("ข้าวไก่กระเทียม",3 , "ใส่กระเทียมลงไปผัดด้วยกัน ปรุงรสด้วย น้ำตาล และซีอิ้วขาว"));
		arrayHowToCook.add(new HowToCook("ข้าวไก่กระเทียม",4 , "ตักราดข้าวเสิร์ฟได้"));
		arrayHowToCook.add(new HowToCook("น้ำพริกอ่องอกไก่",1 , "โขลกพริกแห้ง กระเทียม หอมแดงและกะปิ ให้เข้ากัน"));
		arrayHowToCook.add(new HowToCook("น้ำพริกอ่องอกไก่",2 , "ตั้งไฟ ใส่น้ำมันจนร้อนและนำส่วนผสมที่โขลกไว้ไปผัดจนหอม"));
		arrayHowToCook.add(new HowToCook("น้ำพริกอ่องอกไก่",3 , "ใส่อกไก่สับลงไปผัด "));
		arrayHowToCook.add(new HowToCook("น้ำพริกอ่องอกไก่",4 , "เมื่อไก่เริ่มสุกใส่มะเขือเทศลงไปผัด และปรุงรสด้วยน้ำปลา น้ำตาล ปละน้ำส้มสายชูตามใจชอบ"));
		arrayHowToCook.add(new HowToCook("น้ำพริกอ่องอกไก่",5 , "ผัดจนมะเขือเทศเริ่มนิ่มและแหลกเป็นชิ้นเล็กๆ จึงปิดไฟ ยกลงจากเตา"));
		arrayHowToCook.add(new HowToCook("น้ำพริกอ่องอกไก่",6 , "ตักใส่ถ้วย เสริฟคู่กับผักสดและไข่ต้ม"));
		arrayHowToCook.add(new HowToCook("ผัดกะเพราไก่",1 , "ตั้งกระทะให้ร้อน ใส่น้ำมัน ใส่พริกกับกระเทียมตำลงผัดบนไฟกลางๆจนหอม ใส่อกไก่หั่นบางๆลงผัดไฟกลางๆจนสุก"));
		arrayHowToCook.add(new HowToCook("ผัดกะเพราไก่",2 , "ปรุงรสด้วยน้ำตาลทราย น้ำปลา และซีอิ๊วดำ"));
		arrayHowToCook.add(new HowToCook("ผัดกะเพราไก่",3 , "ใส่ใบกะเพราแล้วผัดให้เข้ากันอีกที"));
		arrayHowToCook.add(new HowToCook("ฟักทองผัดไข่",1 , "ตั้งกระทะให้ร้อน ใส่น้ำมันลงไป พอน้ำมันร้อนก็ใส่กระเทียมสับลงผัดบนไฟกลางๆให้กระเทียมเหลือง หอม"));
		arrayHowToCook.add(new HowToCook("ฟักทองผัดไข่",2 , "ใส่น้ำเปล่าลงไป ปริมาณที่ใส่ก็ขึ้นอยู่กับความเหนียวของเนื้อฟักทอง ขนาดชิ้นที่หั่น และความชอบที่จะให้ผัดฟักทองเป็นชิ้น - เละ มากขนาดไหนนะครับ ถ้าไม่แน่ใจให้ใส่ทีละน้อยๆก่อน ให้ท่วมประมาณครึ่งนึงของชิ้นฟักทองในกระทะก่อน"));
		arrayHowToCook.add(new HowToCook("ฟักทองผัดไข่",3 , "เร่งไฟขึ้นนิดนึงแล้วผัดต่อไปเรื่อยๆ"));
		arrayHowToCook.add(new HowToCook("ฟักทองผัดไข่",4 , "พอฟักทองทำท่าจะสุก ลองใช้ส้อมจิ้มๆดู จะรู้สึกว่าจิ้มง่ายแต่จะแข็งๆตรงกลางอยู่ก็ปรุงรสด้วยน้ำตาล น้ำปลาได้"));
		arrayHowToCook.add(new HowToCook("ฟักทองผัดไข่",5 , "ผัดต่อจนกว่าชิ้นฟักทองจะใส เปลี่ยนเป็นสีเข้มขึ้นแสดงว่าสุกแล้ว โกยฟักทองไปไว้ข้างหนึ่งของกระทะ ตอกไข่ใส่ลงไป ใช้มุมตะหลิวคนๆให้ไข่จับตัวเป็นก้อนนิ่มๆ แล้วโกยฟักทองมากลบเอาไว้ พอกะว่าไข่เริ่มสุกก็ใส่พริกชี้ฟ้า แล้วกลับ 1 ครั้ง ใส่ต้นหอมหั่นท่อน แล้วผัดเบาๆจนเข้ากันดี"));
		arrayHowToCook.add(new HowToCook("สุกี้กุ้งสดแห้ง",1 , "เตรียมล้างผักต่างๆ ผักบุ้ง ผักกาดขาว และเด็ดหั่นพักไว้"));
		arrayHowToCook.add(new HowToCook("สุกี้กุ้งสดแห้ง",2 , "วุ้นเส้นแช่น้ำ กระเทียมสับละเอียด ตอกไข่รอใส่ถ้วยไว้ "));
		arrayHowToCook.add(new HowToCook("สุกี้กุ้งสดแห้ง",3 , "นำผักทุกชนิดใส่ชามหรือจานและใส่วุ้นเส้นเตรียมไว้"));
		arrayHowToCook.add(new HowToCook("สุกี้กุ้งสดแห้ง",4 , "ตั้งกะทะใส่น้ำมันและกระเทียมผัดพอเหลือง ใส่กุ้ง ไข่ไก่ ผัดพอสุก ใส่น้ำมันหอย ซีอิ๊วขาว ใส่ผักที่เราเตรียมไว้ ใส่ซอสน้ำจิ้มสุกี้ ผัดให้เข้ากัน ตักใส่จาน เตรียมเสริฟ"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",1 , "ตอกไข่ใส่ชาม ใส่ซีอิ๊วขาว ตีให้ฟู"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",2 , "เจียวไข่ให้ฟู"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",3 , "ตักขึ้นมาแล้วพักให้สะเด็ดน้ำมัน"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",4 , "หั่นให้เป็นชิ้นๆ พอดีคำ"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",5 , "ต้มน้ำให้เดือด"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",6 , "ใส่เกลือและซีอิ๊วขาว"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",7 , "เมื่อน้ำเดือด ใส่หมูสับ"));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",8 , "เมื่อหมูสุกแล้ว (สังเกตได้ว่าหมูจะลอย) ใส่ไข่เจียวที่หั่นเป็นชิ้นแล้วลงไป "));
		arrayHowToCook.add(new HowToCook("แกงจืดไข่",9 , "เมื่อจะเสิร์ฟ โรยผักชีและต้นหอมหั่นท่อน"));
		arrayHowToCook.add(new HowToCook("ไก่ทอดหาดใหญ่",1 , "เตรียมเจียวหอม ปอกเปลือกหัวหอมแดงล้างซอยเป็นชิ้นเล็ก ๆ ขนาดเท่ากันผึ่งลมผึ่งแดดให้แห้งจะทอดกรอบง่าย ใช้ไฟกลางไปทางอ่อน เมื่อหัวหอมใสใส่เกลือลงไป 1/4 ช้อนชา จะช่วยให้หัวหอมเจียวกรอบทนขึ้น พอเริ่มเหลืองอ่อน ๆ สักพักช้อนหัวหอมขึ้น รอให้เย็นแล้วเก็บใส่กล่องมีฝาปิด"));
		arrayHowToCook.add(new HowToCook("ไก่ทอดหาดใหญ่",2 , "โขลกพริกไทย กระเทียม ให้ละเอียดนำไปผสมไก่ ซีอิ้วขาว น้ำมันหอย น้ำปลา ผงปรุงรส เกลือ น้ำตาลทราย คน ๆ ขยำให้ทั่วกัน หมักทิ้งไว้ประมาณ 3 ชั่วโมงขึ้นไปใส่ตู้เย็นทิ้งไว้ 1 คืน หากทอดไก่ไม่หมด ให้ใส่กล่องมีฝาปิดหรือถุงพลาสติกรัดยางแล้วแช่ฟรีซ"));
		arrayHowToCook.add(new HowToCook("ไก่ทอดหาดใหญ่",3 , "เตรียมทอด แป้งสาลีจะไม่ใส่ในขึ้นตอนหมักจะใส่ตอนก่อนจะทอด นำแป้งสาลีอเนกประสงค์ โรยไปบนไก่คนให้เข้ากัน พักไว้"));
		arrayHowToCook.add(new HowToCook("ไก่ทอดหาดใหญ่",4 , "ใส่น้ำมันเพิ่มในกระทะที่เหลือจากทอดกระเทียมเจียว กะปริมาณน้ำมันท่วมไก่ ใช้ไฟปานกลางให้น้ำมันร้อน พอน้ำมนลดไฟเหลือไฟอ่อน หย่อนไก่ลงด้านนึงเริ่มสุกพลิกกลับด้าน พอผิวด้านนอกไก่เริ่มสุกทั้งสองด้าน เพิ่มไฟเป็นปานกลาง ทอดต่ออีกประมาณ 5 นาที รวมใช้เวลาทอดนาน 10 นาทีขึ้นไปตักไก่ขึ้นให้สะเด็ดน้ำมันพักในตะแกรง"));
		arrayHowToCook.add(new HowToCook("ไก่ทอดหาดใหญ่",5 , "จัดเสริ์ฟกับข้าวเหนียวหรือข้าวสวยร้อน ๆ โรยหน้าด้วยหอมแดงเจียว"));
		arrayHowToCook.add(new HowToCook("ไข่เจียว เห็ดเข็มทอง",1 , "ล้างเห็ดเข็มทองให้สะอาด หั่นเป็นสี่เหลี่ยม (ไม่ควรหั่นยาวไป หรือ สั้นจนเกินไป)"));
		arrayHowToCook.add(new HowToCook("ไข่เจียว เห็ดเข็มทอง",2 , "ตอกไข่ใส่ถ้วยแล้วจึงใส่เห็ดเข็มทองลงไป ตามด้วยหมูสับ ซอสปรุงรสคนให้เข้ากัน"));
		arrayHowToCook.add(new HowToCook("ไข่เจียว เห็ดเข็มทอง",3 , "กะทะตั้งไฟปานกลางใส่น้ำมันพอร้อน จึงใส่ไข่ลงไป ไว้พอด้านหนึ่งสุกก็กลับอีกด้าน(ถ้าตั้งไฟแรงจนเกินไป ไข่เจียวเป็นสีดำ ทำให้ดูไม่น่าทาน)"));
		arrayHowToCook.add(new HowToCook("ไข่เจียว เห็ดเข็มทอง",4 , "จัดใส่จานพร้อมทานได้เลยทันที"));
		
		arrayHasIngredients.add(new HasIngredients("Pork Ball", "Minced Pork"));
		arrayHasIngredients.add(new HasIngredients("Pork Ball", "Brown Onion"));
		arrayHasIngredients.add(new HasIngredients("Pork Ball", "Corainder"));
		arrayHasIngredients.add(new HasIngredients("Pork Ball", "Sesame Oil"));
		arrayHasIngredients.add(new HasIngredients("Pork Ball", "Soy Sauce"));
		arrayHasIngredients.add(new HasIngredients("Pork Ball", "Egg"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Wide Rice Noodle"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Minced Pork"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Soy Sauce"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Brown Onion"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Tomato"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Oil"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Corainder"));
		arrayHasIngredients.add(new HasIngredients("ก๋วยเตี๋ยวราดหน้าหมูสับ", "Lettuce"));
		arrayHasIngredients.add(new HasIngredients("ข้าวต้มหมูทรงเครื่อง", "Rice"));
		arrayHasIngredients.add(new HasIngredients("ข้าวต้มหมูทรงเครื่อง", "Minced Pork"));
		arrayHasIngredients.add(new HasIngredients("ข้าวต้มหมูทรงเครื่อง", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("ข้าวต้มหมูทรงเครื่อง", "Corainder"));
		arrayHasIngredients.add(new HasIngredients("ข้าวต้มหมูทรงเครื่อง", "Spring Onion"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Shrimp"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Egg"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Oil"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Rice"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Spring Onion"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Corainder"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Chili"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Shallots"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Cucumber"));
		arrayHasIngredients.add(new HasIngredients("ข้าวผัดกุ้ง", "Lettuce"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Chicken Breast"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Corainder"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Rice"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Ginger"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Oil"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Chili"));
		arrayHasIngredients.add(new HasIngredients("ข้าวมันไก่", "Soy Sauce"));
		arrayHasIngredients.add(new HasIngredients("ข้าวไก่กระเทียม", "Chicken"));
		arrayHasIngredients.add(new HasIngredients("ข้าวไก่กระเทียม", "Chicken Breast"));
		arrayHasIngredients.add(new HasIngredients("ข้าวไก่กระเทียม", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("ข้าวไก่กระเทียม", "Soy Sauce"));
		arrayHasIngredients.add(new HasIngredients("น้ำพริกอ่องอกไก่", "Chicken"));
		arrayHasIngredients.add(new HasIngredients("น้ำพริกอ่องอกไก่", "Chicken Breast"));
		arrayHasIngredients.add(new HasIngredients("น้ำพริกอ่องอกไก่", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("น้ำพริกอ่องอกไก่", "Shallots"));
		arrayHasIngredients.add(new HasIngredients("น้ำพริกอ่องอกไก่", "Tomato"));
		arrayHasIngredients.add(new HasIngredients("ผัดกะเพราไก่", "Chicken"));
		arrayHasIngredients.add(new HasIngredients("ผัดกะเพราไก่", "Chicken Breast"));
		arrayHasIngredients.add(new HasIngredients("ผัดกะเพราไก่", "Chili"));
		arrayHasIngredients.add(new HasIngredients("ผัดกะเพราไก่", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("ผัดกะเพราไก่", "Oil"));
		arrayHasIngredients.add(new HasIngredients("ฟักทองผัดไข่", "Pumpkin"));
		arrayHasIngredients.add(new HasIngredients("ฟักทองผัดไข่", "Chili"));
		arrayHasIngredients.add(new HasIngredients("ฟักทองผัดไข่", "Spring Onion"));
		arrayHasIngredients.add(new HasIngredients("ฟักทองผัดไข่", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("ฟักทองผัดไข่", "Oil"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Cabbage"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Morning Glory"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Vermicelli"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Shrimp"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Chicken"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Pork"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Squid"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Oyster Sauce"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Soy Sauce"));
		arrayHasIngredients.add(new HasIngredients("สุกี้กุ้งสดแห้ง", "Suki Sauce"));
		arrayHasIngredients.add(new HasIngredients("แกงจืดไข่", "Egg"));
		arrayHasIngredients.add(new HasIngredients("แกงจืดไข่", "Minced Pork"));
		arrayHasIngredients.add(new HasIngredients("แกงจืดไข่", "Spring Onion"));
		arrayHasIngredients.add(new HasIngredients("แกงจืดไข่", "Soy Sauce"));
		arrayHasIngredients.add(new HasIngredients("แกงจืดไข่", "Corainder"));
		arrayHasIngredients.add(new HasIngredients("ไก่ทอดหาดใหญ่", "Chicken Breast"));
		arrayHasIngredients.add(new HasIngredients("ไก่ทอดหาดใหญ่", "Garlic"));
		arrayHasIngredients.add(new HasIngredients("ไก่ทอดหาดใหญ่", "Soy Sauce"));
		arrayHasIngredients.add(new HasIngredients("ไก่ทอดหาดใหญ่", "Oyster Sauce"));
		arrayHasIngredients.add(new HasIngredients("ไก่ทอดหาดใหญ่", "Oil"));
		arrayHasIngredients.add(new HasIngredients("ไก่ทอดหาดใหญ่", "Shallots"));
		arrayHasIngredients.add(new HasIngredients("ไข่เจียว เห็ดเข็มทอง", "Enokitake"));
		arrayHasIngredients.add(new HasIngredients("ไข่เจียว เห็ดเข็มทอง", "Egg"));
		arrayHasIngredients.add(new HasIngredients("ไข่เจียว เห็ดเข็มทอง", "Minced Pork"));
		arrayHasIngredients.add(new HasIngredients("ไข่เจียว เห็ดเข็มทอง", "Oil"));
	}

}
