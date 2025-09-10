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

import tkinter, math

class App(tkinter.Tk):
    '''I represent the Passphrase generator application.'''

    __minimumPassphraseLength = 6
    __appHeight = 170
    __appWidth = 800

    def __init__(self):
        super().__init__()
        self.title('Passphrase generator')
        self.geometry(f'{App.getAppWidth()}x{App.getAppHeight()}')
        self.wordList = {}
        self.passphraseFileNameStringVar = tkinter.StringVar()
        self.passphraseLabelStringVar = tkinter.StringVar()
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
                                            from_ = App.getMinimumPassphraseLength(),
                                            to = math.inf,
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
        '''Runs this app by calling tkinter's mainloop.  Returns self.'''
        self.mainloop()
        return self

    @property
    def generatePassphraseMethod(self):
        '''This method is called on clicking "Generate Passphrase" button.'''
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
        '''A method which is called on clicking "Choose File".'''
        return self.__changePassphraseFileMethod
    
    def __changePassphraseFileMethod(self):
        print('Passphrase file changed.')

    def __generatePassphraseMethod(self):
        print('Passphrase generated.')

    @property
    def copyPassphraseMethod(self):
        '''A method that is called on clicking "Copy" button.'''
        return self.__copyPassphraseMethod
    
    def __copyPassphraseMethod(self):
        print('Passphrase copied.')

    @property
    def numberOfDice(self):
        '''Total number of dice.  This value is set automatically by reading the choosen passphrase file.'''
        return self.__numberOfDice

    @numberOfDice.setter
    def numberOfDice(self, n):
        self.__numberOfDice = n

    @property
    def numberOfWords(self):
        '''Number of words.'''
        return self.__numberOfWords

    @numberOfWords.setter
    def numberOfWords(self, n):
        self.__numberOfWords = n

    def loadPassphraseFile(self, file_path):
        '''Loads passphrase file and stores all the words in "wordList".  Returns self.'''
        with open(file_path, 'r') as passphrase_file:
            lines = passphrase_file.readlines()
            self.numberOfDice = len(lines[0].split()[0])
            for line in lines:
                line = line.split()
                self.wordList.setdefault(int(line[0]), line[1])
            self.passphraseFileNameStringVar.set(passphrase_file.name)

        return self

    @property
    def passphraseFileNameStringVar(self):
        '''A string variable that stores the passphrase file name.'''
        return self.__passphraseFileNameStringVar

    @passphraseFileNameStringVar.setter
    def passphraseFileNameStringVar(self, string_variable):
        self.__passphraseFileNameStringVar = string_variable

    @property
    def wordList(self):
        '''A dictionary where the dice combinations are keys and words are values.'''
        return self.__wordList

    @wordList.setter
    def wordList(self, dictionary):
        self.__wordList = dictionary
        
    @property
    def generatePassphraseButton(self):
        '''A button that generates the passphrase on clicking.'''
        return self.__generatePassphraseButton

    @generatePassphraseButton.setter
    def generatePassphraseButton(self, button):
        self.__generatePassphraseButton = button

    @property
    def passphraseFileLabel(self):
        '''A label which stores "Passphrase file" text.'''
        return self.__passphraseFileLabel

    @passphraseFileLabel.setter
    def passphraseFileLabel(self, label):
        self.__passphraseFileLabel = label

    @property
    def passphraseFileNameLabel(self):
        '''This label stores the passphrase file name.'''
        return self.__passphraseFileNameLabel

    @passphraseFileNameLabel.setter
    def passphraseFileNameLabel(self, label):
        self.__passphraseFileNameLabel = label

    @property
    def choosePassphraseFileButton(self):
        '''A button that opens the file dialog to choose the passphrase file.'''
        return self.__choosePassphraseFileButton

    @choosePassphraseFileButton.setter
    def choosePassphraseFileButton(self, button):
        self.__choosePassphraseFileButton = button

    @property
    def numberOfWordsLabel(self):
        '''A label whose text is "Number of words".'''
        return self.__numberOfWordsLabel

    @numberOfWordsLabel.setter
    def numberOfWordsLabel(self, label):
        self.__numberOfWordsLabel = label

    @property
    def numberOfWordsSelector(self):
        '''A spinbox used to select the number of words.'''
        return self.__numberOfWordsSelector

    @numberOfWordsSelector.setter
    def numberOfWordsSelector(self, spinner):
        self.__numberOfWordsSelector = spinner

    @property
    def passphraseLabel(self):
        '''A label whose text is "Passphrase".'''
        return self.__passphraseLabel

    @passphraseLabel.setter
    def passphraseLabel(self, label):
        self.__passphraseLabel = label

    @property
    def copyButton(self):
        '''The "Copy" button.'''
        return self.__copyButton

    @copyButton.setter
    def copyButton(self, button):
        self.__copyButton = button

    @classmethod
    def getMinimumPassphraseLength(cls):
        '''Returns minimum passphrase length.'''
        return cls.__minimumPassphraseLength

    @classmethod
    def getAppHeight(cls):
        '''Returns height of the application window.'''
        return cls.__appHeight

    @classmethod
    def getAppWidth(cls):
        '''Returns width of the application window.'''
        return cls.__appWidth


App().run()
