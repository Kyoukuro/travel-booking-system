Write-Host "Compiling Java files..."

# clear out folder
if (Test-Path out) {
    Remove-Item -Recurse -Force out
}
mkdir out | Out-Null

# collect all .java files automatically
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }

javac -d out $files

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Compile failed."
    exit
}

Write-Host "Running application..."

# detect main package
$mainClass = "Main"
$mainFile = Get-ChildItem -Recurse -Filter Main.java | Select-Object -First 1

if (Select-String -Path $mainFile.FullName -Pattern "package app") {
    $mainClass = "app.Main"
}

java -cp out $mainClass
