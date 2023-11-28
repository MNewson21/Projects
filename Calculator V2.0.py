#!/usr/bin/python3
import tkinter as tk
import tkinter.ttk as ttk
from math import sqrt
global points
points = 0
root = tk.Tk()
root.title("Calculator")

class CalculatorV20App:
    def __init__(self, master=None):
        # build ui
        toplevel1 = tk.Toplevel(master, container=False)
        toplevel1.geometry("320x720")
        toplevel1.resizable(False, False)
        self.Enter = ttk.Frame(toplevel1)
        self.Enter.configure(height=200, width=200)
        
        
        self.text1 = tk.Text(self.Enter)
        self.text1.configure(height=10, width=50)
        self.temp = ""
        self._text_ = self.temp
        
        self.text1.insert("0.0", self._text_)
        self.text1.place(anchor="nw", height=185, width=320, x=0, y=0)
        
        
        
        
        
        self.num1 = ttk.Button(self.Enter)
        self.num1.configure(text='1')
        self.num1.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=0,
            y=405)
        self.num1.configure(command=self.on_num1_click)
        self.num2 = ttk.Button(self.Enter)
        self.num2.configure(text='2')
        self.num2.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=80,
            y=405)
        self.num2.configure(command=self.on_num2_click)
        self.num3 = ttk.Button(self.Enter)
        self.num3.configure(text='3')
        self.num3.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=160,
            y=405)
        self.num3.configure(command=self.on_num3_click)
        self.num4 = ttk.Button(self.Enter)
        self.num4.configure(text='4')
        self.num4.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=0,
            y=485)
        self.num4.configure(command=self.on_num4_click)
        self.num5 = ttk.Button(self.Enter)
        self.num5.configure(text='5')
        self.num5.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=80,
            y=485)
        self.num5.configure(command=self.on_num5_click)
        self.num6 = ttk.Button(self.Enter)
        self.num6.configure(text='6')
        self.num6.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=160,
            y=485)
        self.num6.configure(command=self.on_num6_click)
        self.num7 = ttk.Button(self.Enter)
        self.num7.configure(text='7')
        self.num7.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=0,
            y=565)
        self.num7.configure(command=self.on_num7_click)
        self.num8 = ttk.Button(self.Enter)
        self.num8.configure(text='8')
        self.num8.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=80,
            y=565)
        self.num8.configure(command=self.on_num8_click)
        self.num9 = ttk.Button(self.Enter)
        self.num9.configure(text='9')
        self.num9.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=160,
            y=565)
        self.num9.configure(command=self.on_num9_click)
        self.change = ttk.Button(self.Enter)
        self.change.configure(text='±')
        self.change.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=0,
            y=645)
        self.change.configure(command=self.on_change_click)
        self.num0 = ttk.Button(self.Enter)
        self.num0.configure(text='0')
        self.num0.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=80,
            y=645)
        self.num0.configure(command=self.on_num0_click)
        self.period = ttk.Button(self.Enter)
        self.period.configure(text='.')
        self.period.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=160,
            y=645)
        self.period.configure(command=self.on_period_click)
        self.multiply = ttk.Button(self.Enter)
        self.multiply.configure(text='X')
        self.multiply.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=240,
            y=405)
        self.multiply.configure(command=self.on_multiply_click)
        self.minus = ttk.Button(self.Enter)
        self.minus.configure(text='-')
        self.minus.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=240,
            y=485)
        self.minus.configure(command=self.on_minus_click)
        self.add = ttk.Button(self.Enter)
        self.add.configure(text='+')
        self.add.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=240,
            y=565)
        self.add.configure(command=self.on_add_click)
        self.equals = ttk.Button(self.Enter)
        self.equals.configure(text='=')
        self.equals.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=240,
            y=645)
        self.equals.configure(command=self.on_equals_click)
        self.CE = ttk.Button(self.Enter)
        self.CE.configure(text='CE')
        self.CE.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=0,
            y=325)
        self.CE.configure(command=self.on_CE_click)
        self.C = ttk.Button(self.Enter)
        self.C.configure(text='C')
        self.C.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=80,
            y=325)
        self.C.configure(command=self.on_c_click)
        self.backspace = ttk.Button(self.Enter)
        self.backspace.configure(text='⌫')
        self.backspace.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=160,
            y=325)
        self.backspace.configure(command=self.on_backspace_click)
        self.divide = ttk.Button(self.Enter)
        self.divide.configure(text='÷')
        self.divide.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=240,
            y=325)
        self.divide.configure(command=self.on_divide_click)
        self.number_inverse = ttk.Button(self.Enter)
        self.number_inverse.configure(text='⅟𝑥')
        self.number_inverse.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=240,
            y=245)
        self.number_inverse.configure(command=self.on_inverse_click)
        self.x_squared = ttk.Button(self.Enter)
        self.x_squared.configure(text='𝑥²')
        self.x_squared.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=160,
            y=245)
        self.x_squared.configure(command=self.on_x_squared_click)
        self.square_Root = ttk.Button(self.Enter)
        self.square_Root.configure(text='√')
        self.square_Root.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=80,
            y=245)
        self.square_Root.configure(command=self.on_square_root_click)
        self.percentage = ttk.Button(self.Enter)
        self.percentage.configure(text='%')
        self.percentage.place(
            anchor="nw",
            height=80,
            relx=0.0,
            rely=0.0,
            width=80,
            x=0,
            y=245)
        self.percentage.configure(command=self.on_percentage_click)
        self.MC = ttk.Button(self.Enter)
        self.MC.configure(text='MC')
        self.MC.place(
            anchor="nw",
            height=60,
            relx=0.0,
            rely=0.0,
            width=54,
            x=0,
            y=185)
        self.MC.configure(command=self.on_mc_click)
        self.MR = ttk.Button(self.Enter)
        self.MR.configure(text='MR')
        self.MR.place(
            anchor="nw",
            height=60,
            relx=0.0,
            rely=0.0,
            width=54,
            x=54,
            y=185)
        self.MR.configure(command=self.on_mr_click)
        self.MPLUS = ttk.Button(self.Enter)
        self.MPLUS.configure(text='M+')
        self.MPLUS.place(
            anchor="nw",
            height=60,
            relx=0.0,
            rely=0.0,
            width=54,
            x=108,
            y=185)
        self.MPLUS.configure(command=self.on_mplus_click)
        self.MMINUS = ttk.Button(self.Enter)
        self.MMINUS.configure(text='M-')
        self.MMINUS.place(
            anchor="nw",
            height=60,
            relx=0.0,
            rely=0.0,
            width=54,
            x=162,
            y=185)
        self.MMINUS.configure(command=self.on_mminus_click)
        self.MS = ttk.Button(self.Enter)
        self.MS.configure(text='MS')
        self.MS.place(
            anchor="nw",
            height=60,
            relx=0.0,
            rely=0.0,
            width=54,
            x=216,
            y=185)
        self.MS.configure(command=self.on_ms_click)
        self.MDOWN = ttk.Button(self.Enter)
        self.MDOWN.configure(text='M^')
        self.MDOWN.place(
            anchor="nw",
            height=60,
            relx=0.0,
            rely=0.0,
            width=54,
            x=270,
            y=185)
        self.MDOWN.configure(command=self.on_mdown_click)
        self.Enter.grid(column=0, row=0, sticky="nsew")
        toplevel1.grid_anchor("center")
        toplevel1.rowconfigure(0, weight=1)
        toplevel1.columnconfigure(0, weight=1)

        # Main widget
        self.mainwindow = toplevel1
    
    def run(self):
        self.mainwindow.mainloop()
        pass

    def on_num1_click(self):
        self._text_ = "1"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
        
        
    def on_num2_click(self):
        self._text_ = "2"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
        
        
    def on_num3_click(self):
        self._text_ = "3"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_num4_click(self):
        self._text_ = "4"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_num5_click(self):
        self._text_ = "5"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_num6_click(self):
        self._text_ = "6"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_num7_click(self):
        self._text_ = "7"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_num8_click(self):
        self._text_ = "8"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_num9_click(self):
        self._text_ = "9"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_change_click(self):
        text = self.text1.get("1.0", "end-1c")  # Get the text in the text widget
        strlength = len(text)
                   
        last_char = text[strlength-2:strlength]  # Remove the last character
    

        if last_char == "+ ":

            print("+ to minus")
            #print(self.temp.replace("+ ", "- "))

            
            text1 = self.text1.get("1.0", "end-2c")  # Get the text in the text widget
            text1 = text1[:-2]  # Remove the last two characters
            self.text1.delete("1.0", "end")  # Clear the text after the last two characters
            self.text1.insert("1.0", text1)  # Remove the last two characters by inserting a minus string 
            self._text_ = " - "

            self.text1.insert("1000.0", self._text_)
            self._text_ = "- "

            self.temp = self.temp[:-2]
            self.temp = self.temp + (self._text_)        # Updating the equation string
            
            
            
            
        elif last_char == "- ":
            print("minus to plus")
            #print(self.temp.replace("- ", "+ "))

            
            text1 = self.text1.get("1.0", "end-2c")  # Get the text in the text widget
            text1 = text1[:-2]  # Remove the last two characters
            self.text1.delete("1.0", "end")  # Clear the text after the last two characters
            self.text1.insert("1.0", text1)  # Remove the last two characters by inserting a minus string 
            self._text_ = " + "

            self.text1.insert("1000.0", self._text_)
            
            self._text_ = "+ "

            self.temp = self.temp[:-2]

            self.temp = self.temp + (self._text_)        # Updating the equation string            
            
        self._text_ = ""    
            
        print(self.temp)   
            
    def on_num0_click(self):
        self._text_ = "0"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
        
    def on_period_click(self):
        
        self._text_ = "."
        #global points
        
        #if points != 1:
            
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
            
            #points = points + 1
           
        
    def on_multiply_click(self):
        self._text_ = " * "
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
        
        
    def on_minus_click(self):
        self._text_ = " - "
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_add_click(self):
        self._text_ = " + "
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_equals_click(self):
        length = float(len(str(eval(self.temp))))
        print("length", length)
        print(type(length))
        

        
        self.text1.delete("0.0","1000.0")
        length = 0
        
        self.text1.insert("0.0", (eval(self.temp)))
        result = eval(self.temp)
        self.temp = ""
        self.temp = self.temp + str(result)
        points = 0
        
        
    def on_CE_click(self):
        
        symbol = ["+", "-", "/", "*", "**"]
        
        found_last_symbol = False
        
        
        for i in range(len(self.temp) -1, -1, -1):
            
            if self.temp[i-1] in symbol:
                print(f"Found symbol '{self.temp[i]}' at index {i}")
                found_last_symbol = True
                index = i
                break
            
            
            text = self.text1.get("1.0", "end-1c")  # Get the text in the text widget
            text = text[:-1]  # Remove the last character
            self.text1.delete("1.0", "end")  # Clear the text widget
            self.text1.insert("1.0", text)  # Insert the modified text back into the text widget

            self.temp = text
            print(self.temp)    
            

                
        #index = str(float(index))
        

        
        if found_last_symbol:
            print("Last symbol found!")       
        else:
            print("Last symbol not found!")
            
        
    def on_c_click(self):
        self.text1.delete("1.0","1000.0")
        self.temp = ""
    def on_backspace_click(self):
        #length = len(self.temp)              # Length in integer eg. 1, 2 3
        #length = len(self.temp)
        #print(self.temp)
        #print("printed", length)
        
        #length = length - 1
        #length = float(length)          #Convert it to float to get the decimal point eg 1.0 , 2.0
        #length = str(length)            #Convert it to string so delete will accept it
        
       #self.text1.delete("2.0")
        
        #length = float(length)
        #length = int(length)
        #length = length +1
        #print(length)
        text = self.text1.get("1.0", "end-1c")  # Get the text in the text widget
        text = text[:-1]  # Remove the last character
        self.text1.delete("1.0", "end")  # Clear the text widget
        self.text1.insert("1.0", text)  # Insert the modified text back into the text widget

        self.temp = text
        
    def on_divide_click(self):
        self._text_ = " / "
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
    def on_inverse_click(self):
        pass
            

            
            
            
    def on_x_squared_click(self):
        self._text_ = " ** "
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)

    def on_square_root_click(self):
        self._text_ = "sqrt(2)"
        self.text1.insert("1000.0", self._text_)
        self.temp = self.temp + (self._text_)
        print(self.temp)
        
        
        
    def on_percentage_click(self):
        pass

    def on_mc_click(self):
        pass

    def on_mr_click(self):
        pass

    def on_mplus_click(self):
        pass

    def on_mminus_click(self):
        pass

    def on_ms_click(self):
        pass

    def on_mdown_click(self):
        pass


if __name__ == "__main__":
    app = CalculatorV20App()
    app.run()
