''' Copyright (C) 2025 Avishek Gorai

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
        self.setWordlist({})
        self.setPassphraseFileNameStringVar(tkinter.StringVar())
        self.setPassphraseLabelStringVar(tkinter.StringVar())
        self.setPassphraseFileLabel(tkinter.Label(self, text = 'Passphrase file'))
        self.setPassphraseFileNameLabel(tkinter.Label(self, textvariable = self.getPassphraseFileNameStringVar()))
        self.setChoosePassphraseFileButton(tkinter.Button(
                                                self,
                                                text = 'Choose file',
                                                command = self.getChangePassphraseFileMethod()
                                            ))
        self.setNumberOfWordsLabel(tkinter.Label(self, text = 'Number of words'))
        self.setNumberOfWordsSelector(tkinter.Spinbox(
                                            self,
                                            from_ = App.getMinimumPassphraseLength(),
                                            to = math.inf,
                                            wrap = False
                                    ))
        self.setPassphraseLabel(tkinter.Label(self, textvariable = self.getPassphraseLabelStringVar()))
        self.setGeneratePassphraseButton(tkinter.Button(
                                            self,
                                            text = 'Generate passphrase',
                                            command = self.getGeneratePassphraseMethod()
                                        ))
        self.setCopyButton(tkinter.Button(
                                    self,
                                    text = 'Copy',
                                    command = self.getCopyPassphraseMethod()
                                  ))
        self.loadPassphraseFile('eff_large_wordlist.txt')

        # Placing widgets

        # First row
        self.getPassphraseFileLabel().place(relx = 0, rely = 0, relwidth = 1/3)
        self.getPassphraseFileNameLabel().place(relx = 1/3, rely = 0, relwidth = 1/3)
        self.getChoosePassphraseFileButton().place(relx = 2/3, rely = 0, relwidth = 1/3)

        # Second row
        self.getNumberOfWordsLabel().place(relx = 0, rely = 1/5, relwidth = 1/2)
        self.getNumberOfWordsSelector().place(relx = 1/2, rely = 1/5, relwidth = 1/2)

        # Third row
        self.getPassphraseLabel().place(relx = 0, rely = 2/5, relwidth = 1)

        # Fourth row
        self.getGeneratePassphraseButton().place(relx = 0, rely = 3/5, relwidth = 1)

        # Fifth row
        self.getCopyButton().place(relx = 0, rely = 4/5, relwidth = 1)

    def getGeneratePassphraseMethod(self):
        return self.generatePassphrase

    def getChangePassphraseFileMethod(self):
        return self.changePassphraseFile

    def getCopyPassphraseMethod(self):
        return self.copyPassphrase

    def run(self):
        self.mainloop()
        return self

    def setPassphraseLabelStringVar(self, string_var):
        self.__passphraseLabelStringVar = string_var
        return self

    def getPassphraseLabelStringVar(self):
        return self.__passphraseLabelStringVar    

    def changePassphraseFile(self):
        print('Passphrase file changed.')

    def generatePassphrase(self):
        print('Passphrase generated.')

    def copyPassphrase(self):
        print('Passphrase copied.')

    def setNumberOfDice(self, n):
        self.__numberOfDice = n
        return self

    def getNumberOfDice(self):
        return self.__numberOfDice

    def getNumberOfWords(self):
        return self.__numberOfWords

    def setNumberOfWords(self, n):
        self.__numberOfWords = n
        return self

    def loadPassphraseFile(self, file_path):        
        with open(file_path, 'r') as passphrase_file:
            lines = passphrase_file.readlines()
            self.setNumberOfDice(len(lines[0].split()[0]))
            for line in lines:
                line = line.split()
                self.getWordlist().setdefault(int(line[0]), line[1])
            self.getPassphraseFileNameStringVar().set(passphrase_file.name)

        return self

    def getPassphraseFileNameStringVar(self):
        return self.__passphraseFileNameStringVar

    def setPassphraseFileNameStringVar(self, string_variable):
        self.__passphraseFileNameStringVar = string_variable
        return self

    def getWordlist(self):
        return self.__wordlist

    def setWordlist(self, dictionary):
        self.__wordlist = dictionary
        return self

    def getGeneratePassphraseButton(self):
        return self.__generatePassphraseButton

    def setGeneratePassphraseButton(self, button):
        self.__generatePassphraseButton = button
        return self

    def getPassphraseFileLabel(self):
        return self.__passphraseFileLabel

    def setPassphraseFileLabel(self, label):
        self.__passphraseFileLabel = label
        return self

    def getPassphraseFileNameLabel(self):
        return self.__passphraseFileNameLabel

    def setPassphraseFileNameLabel(self, label):
        self.__passphraseFileNameLabel = label
        return self

    def getChoosePassphraseFileButton(self):
       return self.__choosePassphraseFileButton

    def setChoosePassphraseFileButton(self, button):
        self.__choosePassphraseFileButton = button
        return self

    def getNumberOfWordsLabel(self):
        return self.__numberOfWordsLabel

    def setNumberOfWordsLabel(self, label):
        self.__numberOfWordsLabel = label
        return self

    def getNumberOfWordsSelector(self):
        return self.__numberOfWordsSelector

    def setNumberOfWordsSelector(self, spinner):
        self.__numberOfWordsSelector = spinner
        return self

    def getPassphraseLabel(self):
        return self.__passphraseLabel

    def setPassphraseLabel(self, label):
        self.__passphraseLabel = label
        return self

    def getCopyButton(self):
        return self.__copyButton

    def setCopyButton(self, button):
        self.__copyButton = button
        return self

    @classmethod
    def getMinimumPassphraseLength(cls):
        return cls.__minimumPassphraseLength

    @classmethod
    def getAppHeight(cls):
        return cls.__appHeight

    @classmethod
    def getAppWidth(cls):
        return cls.__appWidth


App().run()
