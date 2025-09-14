''' Copyright (C) 2025 Avishek Gorai

    Passphrase generator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Passphrase generator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Passphrase generator.  If not, see <https://www.gnu.org/licenses/>.
'''

import tkinter, math, tkinter.filedialog as fileDialog, random

class App(tkinter.Tk):
    '''I represent the Passphrase generator application.'''

    __minimumNumberOfWords = 6
    __appHeight = 170
    __appWidth = 800

    def __init__(self):
        super().__init__()
        self.title('Passphrase generator')
        self.geometry(f'{App.getAppWidth()}x{App.getAppHeight()}')
        self.wordList = {}
        self.numberOfWordsIntVar = tkinter.IntVar(master = self)
        self.passphraseFileNameStringVar = tkinter.StringVar(master = self)
        self.passphraseLabelStringVar = tkinter.StringVar(master = self)
        self.passphraseFileLabel = tkinter.Label(self, text = 'Passphrase file')
        self.passphraseFileNameLabel = tkinter.Label(self, textvariable = self.passphraseFileNameStringVar)
        self.choosePassphraseFileButton = tkinter.Button(
                                                self,
                                                text = 'Choose file',
                                                command = self.changePassphraseFileMethod
                                            )
        self.numberOfWordsLabel = tkinter.Label(self, text = 'Number of words')
        self.numberOfWordsSelector = tkinter.Spinbox(
                                            self,
                                            from_ = App.getMinimumNumberOfWords(),
                                            to = math.inf,
                                            textvariable = self.numberOfWordsIntVar,
                                            wrap = False
                                    )
        self.passphraseLabel = tkinter.Label(self, textvariable = self.passphraseLabelStringVar)
        self.generatePassphraseButton = tkinter.Button(
                                            self,
                                            text = 'Generate passphrase',
                                            command = self.generatePassphraseMethod
                                        )
        self.copyButton = tkinter.Button(
                                    self,
                                    text = 'Copy',
                                    command = self.copyPassphraseMethod
                                  )
        self.loadPassphraseFile('eff_large_wordlist.txt')

        # Placing widgets

        # First row
        self.passphraseFileLabel.place(relx = 0, rely = 0, relwidth = 1/3)
        self.passphraseFileNameLabel.place(relx = 1/3, rely = 0, relwidth = 1/3)
        self.choosePassphraseFileButton.place(relx = 2/3, rely = 0, relwidth = 1/3)

        # Second row
        self.numberOfWordsLabel.place(relx = 0, rely = 1/5, relwidth = 1/2)
        self.numberOfWordsSelector.place(relx = 1/2, rely = 1/5, relwidth = 1/2)

        # Third row
        self.passphraseLabel.place(relx = 0, rely = 2/5, relwidth = 1)

        # Fourth row
        self.generatePassphraseButton.place(relx = 0, rely = 3/5, relwidth = 1)

        # Fifth row
        self.copyButton.place(relx = 0, rely = 4/5, relwidth = 1)

    def run(self):
        '''Runs the app by calling tkinter's mainloop.  Returns self.'''
        self.mainloop()
        return self

    @property
    def numberOfWordsIntVar(self):
        '''Stores number of words.'''
        return self.__numberOfWordsIntVar

    @numberOfWordsIntVar.setter
    def numberOfWordsIntVar(self, intVar):
        self.__numberOfWordsIntVar = intVar

    @property
    def generatePassphraseMethod(self):
        '''This method is used to generate passphrase.'''
        return self.__generatePassphraseMethod      

    @property
    def passphraseLabelStringVar(self):
        '''A string variable that stores the passphrase.'''
        return self.__passphraseLabelStringVar

    @passphraseLabelStringVar.setter
    def passphraseLabelStringVar(self, string_var):
        self.__passphraseLabelStringVar = string_var

    @property
    def changePassphraseFileMethod(self):
        '''This method changes the passphrase file and loads it.'''
        return self.__changePassphraseFileMethod
    
    def __changePassphraseFileMethod(self):
        self.loadPassphraseFile(fileDialog.askopenfilename())
        return self

    def __generatePassphraseMethod(self):
        passphrase = ''
        for i in range(self.numberOfWords):
            index = 0
            for i in range(self.numberOfDice):
                index = index * 10 + random.randint(1, 6)
            passphrase = passphrase + self.wordList.get(index) + ' '
        self.passphraseLabelStringVar.set(passphrase)
        
        return self

    @property
    def copyPassphraseMethod(self):
        '''This method is used to copy the passphrase.'''
        return self.__copyPassphraseMethod
    
    def __copyPassphraseMethod(self):
        self.clipboard_clear()
        self.clipboard_append(self.passphraseLabelStringVar.get())
        return self

    @property
    def numberOfDice(self):
        '''Total number of dice.  This value is set automatically while the passphrase file is being loaded.'''
        return self.__numberOfDice

    @numberOfDice.setter
    def numberOfDice(self, n):
        self.__numberOfDice = n

    @property
    def numberOfWords(self):
        '''Number of words.  App.getMinimumNumberOfWords() returns the default value.'''
        return self.numberOfWordsIntVar.get()

    @numberOfWords.setter
    def numberOfWords(self, n):
        self.numberOfWordsIntVar.set(n)

    def loadPassphraseFile(self, filePath):
        '''Loads passphrase file.  Returns self.'''
        with open(filePath, 'r') as passphrase_file:
            lines = passphrase_file.readlines()
            self.numberOfDice = len(lines[0].split()[0])
            for line in lines:
                line = line.split()
                self.wordList.setdefault(int(line[0]), line[1])
            self.passphraseFileNameStringVar.set(passphrase_file.name)

        return self

    @property
    def passphraseFileNameStringVar(self):
        '''Stores the passphrase file name.'''
        return self.__passphraseFileNameStringVar

    @passphraseFileNameStringVar.setter
    def passphraseFileNameStringVar(self, string_variable):
        self.__passphraseFileNameStringVar = string_variable

    @property
    def wordList(self):
        '''The words and dice combinations are stored here in a dictionary after reading from the passphrase file.'''
        return self.__wordList

    @wordList.setter
    def wordList(self, dictionary):
        self.__wordList = dictionary
        
    @property
    def generatePassphraseButton(self):
        '''Generates passphrase on clicking.'''
        return self.__generatePassphraseButton

    @generatePassphraseButton.setter
    def generatePassphraseButton(self, button):
        self.__generatePassphraseButton = button

    @property
    def passphraseFileLabel(self):
        '''Shows "Passphrase file".'''
        return self.__passphraseFileLabel

    @passphraseFileLabel.setter
    def passphraseFileLabel(self, label):
        self.__passphraseFileLabel = label

    @property
    def passphraseFileNameLabel(self):
        '''Shows the passphrase file name.'''
        return self.__passphraseFileNameLabel

    @passphraseFileNameLabel.setter
    def passphraseFileNameLabel(self, label):
        self.__passphraseFileNameLabel = label

    @property
    def choosePassphraseFileButton(self):
        '''Opens the file dialog to choose the passphrase file.'''
        return self.__choosePassphraseFileButton

    @choosePassphraseFileButton.setter
    def choosePassphraseFileButton(self, button):
        self.__choosePassphraseFileButton = button

    @property
    def numberOfWordsLabel(self):
        '''Shows "Number of words".'''
        return self.__numberOfWordsLabel

    @numberOfWordsLabel.setter
    def numberOfWordsLabel(self, label):
        self.__numberOfWordsLabel = label

    @property
    def numberOfWordsSelector(self):
        '''Used to select number of words.'''
        return self.__numberOfWordsSelector

    @numberOfWordsSelector.setter
    def numberOfWordsSelector(self, spinner):
        self.__numberOfWordsSelector = spinner

    @property
    def passphraseLabel(self):
        '''Shows "Passphrase".'''
        return self.__passphraseLabel

    @passphraseLabel.setter
    def passphraseLabel(self, label):
        self.__passphraseLabel = label

    @property
    def copyButton(self):
        '''The copy button.'''
        return self.__copyButton

    @copyButton.setter
    def copyButton(self, button):
        self.__copyButton = button

    @classmethod
    def getMinimumNumberOfWords(cls):
        '''Returns minimum number of words that is considered safe.'''
        return cls.__minimumNumberOfWords

    @classmethod
    def getAppHeight(cls):
        '''Returns height of the application window.'''
        return cls.__appHeight

    @classmethod
    def getAppWidth(cls):
        '''Returns width of the application window.'''
        return cls.__appWidth


App().run()
