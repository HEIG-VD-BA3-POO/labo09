MD_FILE = rapport.md
OUTPUT = rapport.pdf
ASSETS = assets/

build-pdf:
	pandoc -o $(OUTPUT) $(MD_FILE)

watch-pdf:
	@(find $(ASSETS) -type f; echo $(MD_FILE)) | entr -cs 'make build-pdf'
