class StudentManager {
    constructor() {
        this.students = [];
        this.initializeEventListeners();
        this.startInitialization();
    }

    startInitialization() {
        setTimeout(() => this.initialize(), 0);
    }

    async initialize() {
        await this.loadStudents();
        this.startConnectionCheck();
    }

    async checkConnection() {
        try {
            const response = await fetch('/api/students');
            this.updateConnectionStatus(response.ok);
        } catch (error) {
            this.updateConnectionStatus(false);
        }
    }

    startConnectionCheck() {
        // Initial check
        this.checkConnection();
        // Check every 30 seconds
        setInterval(() => this.checkConnection(), 30000);
    }

    updateConnectionStatus(isConnected) {
        const statusElement = document.getElementById('connectionStatus');
        const iconElement = statusElement.querySelector('.status-icon');
        const textElement = statusElement.querySelector('.status-text');

        iconElement.className = 'status-icon';
        if (isConnected) {
            iconElement.classList.add('connected');
            textElement.textContent = 'Connected to Backend';
        } else {
            iconElement.classList.add('disconnected');
            textElement.textContent = 'Backend Disconnected';
        }
    }

    async loadStudents() {
        try {
            console.log('Fetching updated student list...');
            const response = await fetch('/api/students');
            
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            
            let data = await response.json();
            
            // Sort by ID to ensure proper order
            data = data.sort((a, b) => a.id - b.id);
            
            this.students = data;
            this.updateUI();
            this.updateConnectionStatus(true);
        } catch (error) {
            console.error('Error loading students:', error);
            this.updateConnectionStatus(false);
            this.students = [];
            this.updateUI();
        }
    }

    async addStudent() {
        console.log('Adding student...');
        const student = {
            name: document.getElementById('name').value,
            email: document.getElementById('email').value,
            course: document.getElementById('course').value,
            grade: parseFloat(document.getElementById('grade').value),
            status: 'ACTIVE'
        };
        console.log('Student data:', student);

        try {
            const response = await fetch('/api/students', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(student)
            });
            
            if (!response.ok) {
                const errorData = await response.text();
                throw new Error(errorData || 'Failed to add student');
            }

            const savedStudent = await response.json();
            console.log('Saved student:', savedStudent);
            await this.loadStudents();
            this.clearForm();
        } catch (error) {
            console.error('Error:', error);
            alert(error.message);
        }
    }

    updateUI() {
        this.updateTable();
        this.updateStats();
    }

    updateTable() {
        const tbody = document.getElementById('studentTableBody');
        tbody.innerHTML = '';

        if (!Array.isArray(this.students)) {
            console.error('this.students is not an array:', this.students);
            return;
        }

        this.students.forEach(student => {
            if (!student) {
                console.warn('Encountered null/undefined student');
                return;
            }
            
            const row = `
                <tr>
                    <td>${student.id || ''}</td>
                    <td>${student.name || ''}</td>
                    <td>${student.email || ''}</td>
                    <td>${student.course || ''}</td>
                    <td>${typeof student.grade === 'number' ? student.grade.toFixed(2) : '0.00'}</td>
                    <td>${student.letterGrade || ''}</td>
                    <td>${student.status || ''}</td>
                    <td>
                        <button onclick="studentManager.deleteStudent(${student.id})" class="delete-btn">
                            Delete
                        </button>
                    </td>
                </tr>
            `;
            tbody.innerHTML += row;
        });
    }

    updateStats() {
        document.getElementById('totalStudents').textContent = this.students.length;
        
        const average = this.students.length > 0
            ? this.students.reduce((sum, student) => sum + student.grade, 0) / this.students.length
            : 0;
        
        document.getElementById('classAverage').textContent = average.toFixed(2);
    }

    initializeEventListeners() {
        document.getElementById('studentForm').addEventListener('submit', (e) => {
            e.preventDefault();
            this.addStudent();
        });
    }

    clearForm() {
        document.getElementById('studentForm').reset();
    }

    async deleteStudent(id) {
        try {
            console.log(`Attempting to delete student with ID: ${id}`);
            const response = await fetch(`/api/students/${id}`, {
                method: 'DELETE'
            });
            
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || `Failed to delete student (${response.status})`);
            }
            
            // Add a small delay to allow the backend to complete resequencing
            await new Promise(resolve => setTimeout(resolve, 500));
            await this.loadStudents();
            console.log(`Successfully deleted student with ID: ${id}`);
        } catch (error) {
            console.error('Error:', error);
            alert(error.message);
        }
    }
}

const studentManager = new StudentManager();